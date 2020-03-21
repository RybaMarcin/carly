import { Injectable } from '@angular/core';
import {CompanyManagementService} from "../resources/company-management.service";
import {Company} from "../model/company.model";
import {COMPANY_CONTEXT} from "../model/company-context.model";
import {LocalStorageService} from 'angular-2-local-storage';
import {Roles} from "../model/roles.model";
import {Observable, of} from "rxjs";
import {map, mergeMap} from "rxjs/operators";

@Injectable()
export class CompanyContextService {

  constructor(
    private storageService: LocalStorageService,
    private companyService: CompanyManagementService
  ) {
  }

  getCompanyContext(): Company.Model {
    return this.storageService.get(COMPANY_CONTEXT);
  }

  // setCompanyContext(companyId: string, role: Roles): Observable<boolean> {
  //   let companyObservable = this.findCompanyByIdAndRole(companyId, role);
  //   console.log("Setting company context");
  //   companyObservable.pipe(map(company => console.log(company)));
  //   return companyObservable
  //     .pipe(map(company => this.storageService.set(COMPANY_CONTEXT, company)));
  // }

  setCompanyContext(companyId: string, role: Roles) {

    this.companyService.findCompanyById(companyId).subscribe(company => {
      console.log(610, company);
      console.log("Setting company context: " + company);
      this.storageService.set(COMPANY_CONTEXT, company);
    });

  }


  private findCompanyByIdAndRole(companyId: string, role: Roles): Observable<Company.Model> {

    console.log("Looking for company by id: " + companyId + " and role " + role);

    if(!companyId) {
      return of(null);
    }

    // switch(role) {
    //   case Roles.CARLY_COMPANY:
    //     return this.companyService.findCompanyById(companyId)
    //       .pipe(mergeMap(company => {
    //         if(!company) {
    //           return this.companyService.findPendingCompanyById(companyId);
    //         }
    //         return of(company);
    //       }));
    //   default:
    //     return of(null);
    // }

    this.companyService.findCompanyById(companyId).subscribe(company => {
      console.log(610, company);
    })

  }



}
