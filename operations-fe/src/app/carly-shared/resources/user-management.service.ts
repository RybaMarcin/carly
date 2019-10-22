import { Injectable } from '@angular/core';
import {User} from "../model/user.model";
import {LocalStorageService} from 'angular-2-local-storage';
import {OperationsService} from "./operations.service";
import {CompanyContextService} from "../services/company-context.service";
import {from, Observable} from "rxjs";
import {map, mergeMap} from "rxjs/operators";
import {environment} from "../../../environments/environment";
import jwt_decode from 'jwt-decode';
import {Roles} from "../model/roles.model";
import {CARLY_JWT_TOKEN} from "../model/carly-jwt-token.model";
import {HttpClient} from "@angular/common/http";
import {Customer} from "../model/customer.model";

declare const JWT_TOKEN;
declare const SUBSCRIPTION_KEY;
declare const BASE_API_URL;

const USER_CONTEXT = "userContext";

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  userManagementApi = `${BASE_API_URL}/user-management`;
  user: User;
  invalidJwtTokenMessage = 'Invalid JWT token';

  constructor(
    protected http: HttpClient,
    private storageService: LocalStorageService,
    private operationsService: OperationsService,
    private companyContextService: CompanyContextService
  ) {
  }

  private getUser(): Promise<User> {
    return new Promise<User>((resolve, reject) => this.user ? resolve(this.user) : reject(this.user));
  }

  private cacheUserContext(forceCompanyContextId?: String): Observable<User> {
    return from(this.fetchUser(forceCompanyContextId))
      .pipe(
        map(userContext => {
          console.log("User Context: ");
          console.log(userContext);
         this.user = userContext;
          this.storageService.set(USER_CONTEXT, userContext);
          return userContext;
        }),
        mergeMap(user => this.companyContextService.setCompanyContext(user.companyId, user.role)
          .pipe(map(isCompanyContextStored => user))
        )
      );
  }

  private fetchUser(forceCompanyContextId?: String): Promise<User> {
    console.log("Fetching user, force context id " + forceCompanyContextId);
    const carlyJwtToken = JWT_TOKEN || environment.carlyRole;

    if(!carlyJwtToken) {
      console.log(carlyJwtToken);
      throw new Error(this.invalidJwtTokenMessage);
    }

    const userContext = jwt_decode(carlyJwtToken);
    if (userContext) {
      const foundAuthority = userContext.authorities.find(authority => {
        switch (authority) {
          case Roles.CARLY_OPERATOR:
          case Roles.CARLY_COMPANY:
            return true;
          default:
            return false;
        }
      });

      if (!foundAuthority) {
        this.operationsService.logout().subscribe(uri => window.location.href = uri);
      }
    } else {
      throw new Error(this.invalidJwtTokenMessage);
    }

    const headers = {
      'Ocp-Apim-Trace': 'true',
      'Ocp-Apim-Subscription-Key': SUBSCRIPTION_KEY || environment.subscriptionKey
    }

    headers['Authorization'] = `Carly-Bearer ${carlyJwtToken}`;

    const companyContext = this.companyContextService.getCompanyContext();

    console.log("Company context service returns");
    console.log(companyContext);
    console.log(userContext);

    if (forceCompanyContextId) {
      headers['company-context-id'] = forceCompanyContextId;
    } else if (companyContext) {
      headers['company-context-id'] = companyContext.id;
    }

    return fetch(`${this.userManagementApi}/users/current`, {headers})
      .then(res => {
        this.storageService.set(CARLY_JWT_TOKEN, res.headers.get(CARLY_JWT_TOKEN));
        if(res.status === 401) {
          throw new Error('Status code 401. Authentication error!');
        }
        return res;
      })
      .then(res => res.json())
      .catch(err => {
        this.operationsService.logout().subscribe(uri => window.location.href = uri);
        throw err;
      });
  }

  setCompanyIdInUserContext(forceCompanyContextId: String): Observable<any> {
    return this.cacheUserContext(forceCompanyContextId);
  }

  getUserContext(): Promise<User> {
    if (this.user) {
      return this.getUser();
    } else {
      return this.cacheUserContext().toPromise();
    }
  }

  getUserContext$(): Observable<User> {
    return from(this.getUserContext());
  }

  isUserHasRole$(role: Roles): Observable<boolean> {
    return this.getUserContext$().pipe(map(user => user.role === role));
  }


  //User

  createCustomer(customer: Customer.POST): Observable<Customer.Model> {
    return this.http.post<Customer.POST>(`${this.userManagementApi}`, customer);
  }

  updateCustomer(customer: Customer.PUT): Observable<Customer.Model> {
    return this.http.put<Customer.PUT>(`${this.userManagementApi}`, customer);
  }


}
