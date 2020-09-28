import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Registration} from "../model/registration.model";
import {Observable} from "rxjs";
import {RegistrationCompany} from "../model/registration-company.model";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  //todo: uncomment this when endpoint will be ready.

  // private userManagementApi = `${BASE_API_URL}`;
  private userManagementApi = "api";

  constructor(
    protected http: HttpClient
  ) {
  }


  createNewAccount(newUser: Registration.POST): Observable<Registration.Model> {
    debugger;
    return this.http.post<Registration.POST>(`${this.userManagementApi}/user/registration`, newUser);
  }

  resetPassword(reset: Registration.POST): Observable<Registration.Model> {
    debugger;
    return this.http.post<Registration.POST>(`${this.userManagementApi}/user/reset-password`, reset);
  }

  createNewCompanyAccount(newCompany: RegistrationCompany.POST): Observable<Registration.Model> {
    debugger;
    return this.http.post<RegistrationCompany.POST>(`${this.userManagementApi}/company/registration`, newCompany);
  }


}
