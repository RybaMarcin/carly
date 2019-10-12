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
import {CustomersComponent} from "../carly-shared/components/customers/customers.component";
import {HomeComponent} from "../carly-shared/components/home/home.component";

export const ROUTES: Routes = [
  {
    path: '',
    component: CarlyOperatorComponent,
    children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', component: HomeComponent},
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
      },
      {
        path: 'customers',
        children: [
          {path: '', component: CustomersComponent},
        ]
      }
    ]
  }
];

export const CarlyOperatorRoutingModule: ModuleWithProviders = RouterModule.forChild(ROUTES);
