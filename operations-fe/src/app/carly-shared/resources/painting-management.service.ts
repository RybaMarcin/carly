import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Painting} from "../model/painting.model";

@Injectable({
  providedIn: 'root'
})
export class PaintingManagementService {

  constructor(protected http: HttpClient) {
  }

  paintingManagementApi: string = "api/painting";

  findPaintings(params: HttpParams): Observable<Painting.PaginatedModel> {
    return this.http.get<Painting.PaginatedModel>(`${this.paintingManagementApi}`, {params});
  }

  findPaintingById(id: string): Observable<Painting.Model> {
    return this.http.get<Painting.Model>(`${this.paintingManagementApi}/${id}`);
  }


  createPainting(painting: Painting.POST): Observable<Painting.Model> {
    return this.http.post<Painting.POST>(`${this.paintingManagementApi}`, painting);
  }

  updatePainting(painting: Painting.PUT): Observable<Painting.Model> {
    return this.http.put<Painting.PUT>(`${this.paintingManagementApi}`, painting);
  }

}
