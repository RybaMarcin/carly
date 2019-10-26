import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tires} from "../model/tires.model";

@Injectable({
  providedIn: 'root'
})
export class TiresManagementService {

  constructor(
    private http: HttpClient
  ) {
  }


  tiresManagementApi: string = "api/tires";


  findTires(params: HttpParams): Observable<Tires.PaginatedModel> {
    return this.http.get<Tires.PaginatedModel>(`${this.tiresManagementApi}`, {params});
  }

  findTiresById(id: string): Observable<Tires.Model> {
    return this.http.get<Tires.Model>(`${this.tiresManagementApi}/${id}`);
  }

  createTires(tires: Tires.POST): Observable<Tires.Model> {
    debugger;
    return this.http.post<Tires.POST>(`${this.tiresManagementApi}`, tires);
  }

  updateTires(tires: Tires.PUT): Observable<Tires.Model> {
    return this.http.put<Tires.PUT>(`${this.tiresManagementApi}`, tires);
  }

  getAllTires(): Observable<Tires.Model[]> {
    return this.http.get<Tires.Model[]>(`${this.tiresManagementApi}/all`);
  }

}
