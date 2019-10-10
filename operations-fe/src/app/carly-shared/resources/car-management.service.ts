import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Car} from "../model/car.model";
import {Observable} from "rxjs";
import {ReviewChanges} from "../model/review-changes.model";
import ChangeDecision = ReviewChanges.ChangeDecision;

declare const BASE_API_URL;

@Injectable()
export class CarManagementService {

  constructor(protected http: HttpClient) {
  }


  carlyManagementApi: string = `${BASE_API_URL}`;
  cars: string = 'cars';

  findCars(params: HttpParams): Observable<Car.PaginatedModel> {
    return this.http.get<Car.PaginatedModel>(`${this.carlyManagementApi}/${this.cars}`, {params});
  }

  findCarsByCompanyId(companyId: string, params: HttpParams): Observable<Car.PaginatedModel> {
    return this.http.get<Car.PaginatedModel>(`${this.carlyManagementApi}/cars/company/${companyId}/filter`, {params});
  }

  findCarById(id: string): Observable<Car.Model> {
    return this.http.get<Car.Model>(`${this.carlyManagementApi}/${this.cars}/${id}`);
  }

  findPendingCarById(id: string): Observable<Car.Model> {
    return this.http.get<Car.Model>(`${this.carlyManagementApi}/${this.cars}/pending/${id}`);
  }

  createCar(car: Car.POST): Observable<Car.Model> {
    return this.http.post<Car.Model>(`${this.carlyManagementApi}/${this.cars}`, car);
  }

  createPendingCar(decision: ChangeDecision): Observable<ChangeDecision> {
    return this.http.post<ChangeDecision>(`${this.carlyManagementApi}/${this.cars}/pending`, decision);
  }

  updateCar(car: Car.PUT): Observable<Car.Model> {
    return this.http.put<Car.Model>(`${this.carlyManagementApi}/${this.cars}`, car);
  }

  findAllCars(): Observable<Car.Model[]> {
    return this.http.get<Car.Model[]>(`${this.carlyManagementApi}/${this.cars}/all`);
  }

}
