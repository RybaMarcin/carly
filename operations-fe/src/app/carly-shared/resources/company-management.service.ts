import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Company} from "../model/company.model";
import {ReviewChanges} from "../model/review-changes.model";
import ChangeDecision = ReviewChanges.ChangeDecision;

declare const BASE_API_URL;

@Injectable()
export class CompanyManagementService {

  constructor(protected http: HttpClient) {
  }

  companyManagementApi: string = `${BASE_API_URL}/company`;


  findCompanies(params: HttpParams): Observable<Company.PaginatedModel> {
    return this.http.get<Company.PaginatedModel>(`${this.companyManagementApi}`, {params});
  }

  findCompanyById(id: string): Observable<Company.Model> {
    return this.http.get<Company.Model>(`${this.companyManagementApi}/${id}`);
  }

  findPendingCompanyById(id: string): Observable<Company.Model> {
    return this.http.get<Company.Model>(`${this.companyManagementApi}/pending/${id}`);
  }

  createCompany(company: Company.POST): Observable<Company.Model> {
    debugger;
    return this.http.post<Company.Model>(`${this.companyManagementApi}`, company);
  }

  createPendingCompany(decision: ChangeDecision): Observable<ChangeDecision> {
    return this.http.post<ChangeDecision>(`${this.companyManagementApi}`, decision);
  }

  updateCompany(company: Company.PUT): Observable<Company.Model> {
    return this.http.put<Company.Model>(`${this.companyManagementApi}`, company);
  }

  updateCompanyPendingStatus(decision: ChangeDecision): Observable<ChangeDecision> {
    return this.http.put<ChangeDecision>(`${this.companyManagementApi}`, decision);
  }

  findCompaniesForContext(params: HttpParams): Observable<Company.PaginatedModelForContext> {
    return this.http.get<Company.PaginatedModelForContext>(`${this.companyManagementApi}/all`, {params});
  }

  findAllCompanies(): Observable<Company.Model[]> {
    return this.http.get<Company.Model[]>(`${this.companyManagementApi}/all`);
  }

}
