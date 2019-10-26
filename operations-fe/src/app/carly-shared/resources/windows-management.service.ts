import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Windows} from "../model/windows.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WindowsManagementService {

  constructor(protected http: HttpClient) {
  }

  private windowsManagementApi: string = "api/windows";


  findWindows(params: HttpParams): Observable<Windows.PaginatedModel> {
    return this.http.get<Windows.PaginatedModel>(`${this.windowsManagementApi}`, {params});
  }

  createWindows(windows: Windows.POST): Observable<Windows.Model> {
    return this.http.post<Windows.POST>(`${this.windowsManagementApi}`, windows);
  }

  updateWindows(windows: Windows.PUT): Observable<Windows.Model> {
    return this.http.put<Windows.PUT>(`${this.windowsManagementApi}`, windows);
  }


}
