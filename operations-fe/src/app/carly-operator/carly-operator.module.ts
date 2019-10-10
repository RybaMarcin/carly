import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyAddComponent } from './components/companies/company-add/company-add.component';
import { ContractsMatchingComponent } from './components/contracts-matching/contracts-matching.component';
import {RouterModule} from "@angular/router";
import {CarlyOperatorRoutingModule} from "./carly-operator.routing";
import {CarlySharedModule} from "../carly-shared/carly-shared.module";
import {CarlyOperatorComponent} from "./carly-operator.component";
import {CarlyMatModule} from "../carly-shared/modules/carly-mat.module";
import {Ng5SliderModule} from "ng5-slider";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FileSizeModule} from "ngx-filesize";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {CompaniesComponent} from "./components/companies/companies.component";
import {CarsComponent} from "./components/cars/cars.component";
import {PartsComponent} from "./components/parts/parts.component";
import {PartsAddComponent} from "../carly-company/components/parts/parts-add/parts-add.component";

@NgModule({
  imports: [
    CommonModule,
    CarlyOperatorRoutingModule,
    CarlySharedModule,
    CarlyMatModule,
    Ng5SliderModule,
    FormsModule,
    ReactiveFormsModule,
    FileSizeModule
  ],
  declarations: [
    CarlyOperatorComponent,
    DashboardComponent,
    CompanyAddComponent,
    ContractsMatchingComponent,
    CompaniesComponent,
    CompanyAddComponent,
    CarsComponent,
    PartsComponent,
    PartsAddComponent
  ],
  entryComponents: [
    CarlyOperatorComponent,

  ],
  exports: [RouterModule]
})
export class CarlyOperatorModule { }
