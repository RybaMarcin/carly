import {Injectable} from '@angular/core';
import {User} from "../model/user.model";
import {LocalStorageService} from 'angular-2-local-storage';
import {OperationsService} from "./operations.service";
import {CompanyContextService} from "../services/company-context.service";
import {from, Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Roles} from "../model/roles.model";
import {HttpClient} from "@angular/common/http";
import {Customer} from "../model/customer.model";

declare const BASE_API_URL;

const USER_CONTEXT = "userContext";

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  userManagementApi = `${BASE_API_URL}/user-management`;
  user: User;

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

  // private cacheUserContext(): Promise<any> {
  //   return this.fetchUser()
  //     .then(userContext => {
  //        this.user = userContext;
  //         this.storageService.set(USER_CONTEXT, userContext);
  //
  //         this.companyContextService.setCompanyContext(this.user.companyId, this.user.role);
  //
  //         return userContext;
  //       })
  //     .catch(err => {
  //       throw err;
  //     })
  // }
  //
  // private fetchUser(): Promise<User> {
  //   return fetch()
  // }


  cacheUserContext(user: User) {
    this.storageService.set(USER_CONTEXT, user);

    if(user.role === Roles.CARLY_COMPANY) {
      this.companyContextService.setCompanyContext(this.user.companyId, this.user.role);
    }

  }


  // setCompanyIdInUserContext(): Observable<any> {
  //   return from(this.cacheUserContext());
  // }

  getUserContext(): Promise<User> {
    if (this.user) {
      return this.getUser();
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
