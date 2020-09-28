import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Login} from "../model/login.model";
import {User} from "../model/user.model";
import {Observable} from "rxjs";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // carlyApi = `${BASE_API_URL}`;
  private carlyApi = "api";

  constructor(
    protected http: HttpClient
  ) {
  }

  authenticate(login: User): Observable<User> {
    return this.http.post<User>(`${this.carlyApi}/user/login`, login);
  }

}
