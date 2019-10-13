import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Equipment} from "../model/equipment.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EquipmentManagementService {

  private partManagementApi = "api";
  private partType = "equipment";

  constructor(
    protected http: HttpClient
  ) {
  }

  findEquipment(params: HttpParams): Observable<Equipment.PaginatedModel> {
    return this.http.get<Equipment.PaginatedModel>(`${this.partManagementApi}/${this.partType}`, {params});
  }

  createEquipment(equipment: Equipment.POST): Observable<Equipment.Model> {
    return this.http.post<Equipment.POST>(`${this.partManagementApi}/${this.partType}`, equipment);
  }

  updateEquipment(equipment: Equipment.PUT): Observable<Equipment.Model> {
    return this.http.put<Equipment.PUT>(`${this.partManagementApi}/${this.partType}`, equipment);
  }


}
