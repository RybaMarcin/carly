import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Engine} from "../model/engine.model";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class EngineManagementService {

  constructor(protected http: HttpClient) {
  }

  // engineManagementApi: string = `${BASE_API_URL}`;
  engineManagementApi: string = "api/engines";


  //ENGINE

  findEngines(params: HttpParams): Observable<Engine.PaginatedModel> {
    return this.http.get<Engine.PaginatedModel>(`${this.engineManagementApi}`, {params});
  }

  findEngineById(id: string): Observable<Engine.Model> {
    return this.http.get<Engine.Model>(`${this.engineManagementApi}/${id}`);
  }

  findEnginesByCompany(company: string): Observable<Engine.Model[]> {
    return this.http.get<Engine.Model[]>(`${this.engineManagementApi}/${company}`);
  }

  createEngine(engine: Engine.POST): Observable<Engine.Model> {
    debugger;
    return this.http.post<Engine.Model>(`${this.engineManagementApi}`, engine);
  }

  updateEngine(engine: Engine.PUT): Observable<Engine.Model> {
    return this.http.put<Engine.Model>(`${this.engineManagementApi}`, engine);
  }

  getAllEngines(): Observable<Engine.Model[]> {
    return this.http.get<Engine.Model[]>(`${this.engineManagementApi}/all`);
  }

}
