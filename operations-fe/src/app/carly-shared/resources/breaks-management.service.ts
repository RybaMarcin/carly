import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Breaks} from "../model/breaks.model";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class BreaksManagementService {

  constructor(protected http: HttpClient) {
  }

  // breaksManagementApi: string = `${BASE_API_URL}`;
  breaksManagementApi: string = "api/breaks";

  //BREAKS

  findBreaks(params: HttpParams): Observable<Breaks.PaginatedModel> {
    return this.http.get<Breaks.PaginatedModel>(`${this.breaksManagementApi}`, {params});
  }

  findWheelsById(id: string): Observable<Breaks.Model> {
    return this.http.get<Breaks.Model>(`${this.breaksManagementApi}/${id}`);
  }

  createBreaks(breaks: Breaks.POST): Observable<Breaks.Model> {
    return this.http.post<Breaks.Model>(`${this.breaksManagementApi}`, breaks);
  }

  updateBreaks(breaks: Breaks.PUT): Observable<Breaks.Model> {
    return this.http.put<Breaks.Model>(`${this.breaksManagementApi}`, breaks);
  }




}
