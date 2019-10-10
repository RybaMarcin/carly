import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Registration} from "../model/registration.model";
import {Observable} from "rxjs";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private userManagementApi = `${BASE_API_URL}`;


  constructor(
    protected http: HttpClient
  ) {
  }


  createNewAccount(newUser: Registration.POST): Observable<Registration.Model> {
    debugger;
    return this.http.post<Registration.POST>(`${this.userManagementApi}`, newUser);
  }

}
