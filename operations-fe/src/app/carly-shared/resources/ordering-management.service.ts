import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "../model/order.model";

declare const BASE_API_URL;

@Injectable()
export class OrderingManagementService {

  constructor(protected http: HttpClient) {
  }

  orderManagementApi: string = `${BASE_API_URL}`;


  findOrders(params: HttpParams): Observable<Order.PaginatedModel> {
    return this.http.get<Order.PaginatedModel>(`${this.orderManagementApi}`, {params});
  }

  findOrderById(id: string): Observable<Order.Model> {
    return this.http.get<Order.Model>(`${this.orderManagementApi}/${id}`);
  }




}
