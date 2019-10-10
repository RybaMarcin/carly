// todo: Clear the imports

// Routes
import {RouterModule, Routes} from "@angular/router";
import {CarlyOperatorComponent} from "./carly-operator.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {CompaniesComponent} from "./components/companies/companies.component";
import {CompanyAddComponent} from "./components/companies/company-add/company-add.component";
import {DetailMenuComponent} from "../carly-shared/components/detail-menu/detail-menu.component";
import {CompanyEditComponent} from "../carly-shared/components/company/company-edit/company-edit.component";
import {CarsDashboardComponent} from "../carly-shared/components/cars/cars-dashboard.component";
import {DataType} from "../carly-shared/model/data-type.enum";
import {PartsDashboardComponent} from "../carly-shared/components/parts/parts-dashboard.component";
import {CustomersDashboardComponent} from "../carly-shared/components/customers/customers-dashboard.component";
import {ContractsMatchingComponent} from "./components/contracts-matching/contracts-matching.component";
import {CarsComponent} from "./components/cars/cars.component";
import {CarsAddComponent} from "../carly-company/components/cars/cars-add/cars-add.component";
import {ModuleWithProviders} from "@angular/core";
import {CarEditComponent} from "../carly-shared/components/cars/car-edit/car-edit.component";
import {PartsComponent} from "./components/parts/parts.component";
import {PartsAddComponent} from "../carly-company/components/parts/parts-add/parts-add.component";
import {EnginesComponent} from "../carly-shared/components/engine/engines.component";
import {EngineAddComponent} from "../carly-shared/components/engine/engine-add/engine-add.component";
import {WheelsComponent} from "../carly-shared/components/wheels/wheels.component";
import {WheelsAddComponent} from "../carly-shared/components/wheels/wheels-add/wheels-add.component";
import {BreaksComponent} from "../carly-shared/components/breaks/breaks.component";
import {BreaksAddComponent} from "../carly-shared/components/breaks/breaks-add/breaks-add.component";

export const ROUTES: Routes = [
  {
    path: '',
    component: CarlyOperatorComponent,
    children: [
      {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
      {path: 'dashboard', component: DashboardComponent},
      {
        path: 'companies',
        children: [
          {path: '', component: CompaniesComponent},
          {path: 'add', component: CompanyAddComponent},
          {
            path: 'detail/:id',
            data: {
              navLinks: [
                {label: 'Company data', path: 'edit'},
                {label: 'Cars', path: 'cars'},
                {label: 'Parts', path: 'parts'},
                {label: 'Customers', path: 'customers'},
                {label: 'Contracts & Matching', path: 'contracts-matching'}
              ]
            },
            component: DetailMenuComponent,
            children: [
              {path: '', redirectTo: 'edit', pathMatch: 'full'},
              {path: 'edit', component: CompanyEditComponent},
              {path: 'cars', component: CarsDashboardComponent, data: {dataType: DataType.COMPANY}},
              {path: 'parts', component: PartsDashboardComponent, data: {dataType: DataType.COMPANY}},
              {path: 'customers', component: CustomersDashboardComponent, data: {dataType: DataType.COMPANY}},
              {path: 'contracts-matching', component: ContractsMatchingComponent, data: {dataType: DataType.COMPANY}}
            ]
          }
        ]
      },
      {
        path: 'cars',
        children: [
          {path: '', component: CarsComponent},
          {path: 'add', component: CarsAddComponent},
          {
            path: 'detail/:id',
            data: {
              navLinks: [
                {label: 'Car data', path: 'edit'},
                {label: 'Parts', path: 'parts'},
              ]
            },
            component: DetailMenuComponent,
            children: [
              {path: '', redirectTo: 'edit', pathMatch: 'full'},
              {path: 'edit', component: CarEditComponent},
              {path: 'parts', component: PartsDashboardComponent, data: {dataType: DataType.PART}}
            ]
          }
        ]
      },
      {
        path: 'parts',
        children: [
          {path: '', component: PartsComponent},
          {path: 'add', component: PartsAddComponent},
          {
            path: 'engines',
            children: [
              {path: '', component: EnginesComponent},
              {path: 'add', component: EngineAddComponent}
            ]
          },
          {
            path: 'wheels',
            children: [
              {path: '', component: WheelsComponent},
              {path: 'add', component: WheelsAddComponent}
            ]
          },
          {
            path: 'breaks',
            children: [
              {path: '', component: BreaksComponent},
              {path: 'add', component: BreaksAddComponent}
            ]
          }
        ]
      }
    ]
  }
];

export const CarlyOperatorRoutingModule: ModuleWithProviders = RouterModule.forChild(ROUTES);
