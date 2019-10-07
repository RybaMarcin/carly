import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class OperationsService {

  constructor(private http: HttpClient) {
  }

  logout(): Observable<any> {
    return this.http.get('/logout', {responseType: 'text'});
  }

}
