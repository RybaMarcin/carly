import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Registration} from "../model/registration.model";
import {Observable} from "rxjs";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  //todo: uncomment this when endpoint will be ready.

  // private userManagementApi = `${BASE_API_URL}`;
  private userManagementApi = "http://localhost:8080/user/registration";

  constructor(
    protected http: HttpClient
  ) {
  }


  createNewAccount(newUser: Registration.POST): Observable<Registration.Model> {
    debugger;
    return this.http.post<Registration.POST>(`${this.userManagementApi}`, newUser);
  }

}
