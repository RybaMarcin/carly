import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Engine} from "../model/engine.model";
import {Wheels} from "../model/wheels.model";
import {Part} from "../model/part.model";

declare const BASE_API_URL;

@Injectable()
export class PartManagementService {

  constructor(protected http: HttpClient) {
  }


  partManagementApi: string = `${BASE_API_URL}`;
  engine: string = 'engine';
  tires: string = 'tires';
  painting: string = 'painting';
  wheels: string = 'wheels';
  windows: string = 'windows';
  equipment: string = 'equipment';


  //ALL
  findPartsByCompanyId(companyId: string, params: HttpParams): Observable<Part.PaginatedModel> {
    return this.http.get<Part.PaginatedModel>(`${this.partManagementApi}/${companyId}`, {params});
  }

  findParts(params: HttpParams): Observable<Part.PaginatedModel> {
    return this.http.get<Part.PaginatedModel>(`${this.partManagementApi}`, {params});
  }


  //ENGINE

  findEngines(params: HttpParams): Observable<Engine.PaginatedModel> {
    return this.http.get<Engine.PaginatedModel>(`${this.partManagementApi}/${this.engine}`, {params});
  }

  findEngineById(id: string): Observable<Engine.Model> {
    return this.http.get<Engine.Model>(`${this.partManagementApi}/${this.engine}/${id}`);
  }

  findEnginesByCompany(company: string): Observable<Engine.Model[]> {
    return this.http.get<Engine.Model[]>(`${this.partManagementApi}/${this.engine}/${company}`);
  }

  createEngine(engine: Engine.POST): Observable<Engine.Model> {
    return this.http.post<Engine.Model>(`${this.partManagementApi}/${this.engine}`, engine);
  }

  updateEngine(engine: Engine.PUT): Observable<Engine.Model> {
    return this.http.put<Engine.Model>(`${this.partManagementApi}/${this.engine}`, engine);
  }


  //WHEELS

  findWheels(params: HttpParams): Observable<Wheels.PaginatedModel> {
    return this.http.get<Wheels.PaginatedModel>(`${this.partManagementApi}/${this.wheels}`, {params});
  }

  findWheelsById(id: string): Observable<Wheels.Model> {
    return this.http.get<Wheels.Model>(`${this.partManagementApi}/${this.wheels}/${id}`);
  }

  createWheels(wheels: Wheels.POST): Observable<Wheels.Model> {
    return this.http.post<Wheels.Model>(`${this.partManagementApi}/${this.wheels}`, wheels);
  }

  updateWheels(wheels: Wheels.PUT): Observable<Wheels.Model> {
    return this.http.put<Wheels.Model>(`${this.partManagementApi}/${this.wheels}`, wheels);
  }


}




