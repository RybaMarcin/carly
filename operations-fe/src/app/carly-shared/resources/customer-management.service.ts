import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

declare const BASE_API_URL;

@Injectable({
  providedIn: 'root'
})
export class CustomerManagementService {

  customerManagementApi = `${BASE_API_URL}/customers`;

  constructor(protected http: HttpClient) {
  }

  findCustomersByCompanyId(companyId: string, params: HttpParams): Observable<Customer.PaginatedModel> {
    return this.http.get<Customer.PaginatedModel>(`${this.customerManagementApi}/${companyId}`, {params});
  }

  findCustomers(params: HttpParams): Observable<Customer.PaginatedModel> {
    return this.http.get<Customer.PaginatedModel>(`${this.customerManagementApi}`, {params});
  }

  findCustomerById(customerId: string): Observable<Customer.Model> {
    return this.http.get<Customer.Model>(`${this.customerManagementApi}/${customerId}`);
  }

  findCustomersByCarId(carId: string): Observable<Customer.Model> {
    return this.http.get<Customer.Model>(`${this.customerManagementApi}/${carId}`);
  }

  findCustomersByPartId(partId: string): Observable<Customer.Model> {
    return this.http.get<Customer.Model>(`${this.customerManagementApi}/${partId}`);
  }

  findCustomerByOrderId(orderId: string): Observable<Customer.Model> {
    return this.http.get<Customer.Model>(`${this.customerManagementApi}/${orderId}`);
  }

}
