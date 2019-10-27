import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Wheels} from "../model/wheels.model";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class WheelsManagementService {

  constructor(protected http: HttpClient) {
  }

  // wheelsManagementApi: string = `${BASE_API_URL}`;
  wheelsManagementApi: string = "api/wheels";

  //WHEELS

  findWheels(params: HttpParams): Observable<Wheels.PaginatedModel> {
    return this.http.get<Wheels.PaginatedModel>(`${this.wheelsManagementApi}/wheels`, {params});
  }

  findWheelsById(id: string): Observable<Wheels.Model> {
    return this.http.get<Wheels.Model>(`${this.wheelsManagementApi}/${id}`);
  }

  createWheels(wheels: Wheels.POST): Observable<Wheels.Model> {
    return this.http.post<Wheels.Model>(`${this.wheelsManagementApi}`, wheels);
  }

  updateWheels(wheels: Wheels.PUT): Observable<Wheels.Model> {
    return this.http.put<Wheels.Model>(`${this.wheelsManagementApi}`, wheels);
  }

  getAllWheels(): Observable<Wheels.Model[]> {
    return this.http.get<Wheels.Model[]>(`${this.wheelsManagementApi}/all`);
  }

}
