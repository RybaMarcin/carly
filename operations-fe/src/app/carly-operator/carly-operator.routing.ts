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
import {EquipmentComponent} from "../carly-shared/components/equipment/equipment.component";
import {EquipmentAddComponent} from "../carly-shared/components/equipment/equipment-add/equipment-add.component";
import {TiresComponent} from "../carly-shared/components/tires/tires.component";
import {TiresAddComponent} from "../carly-shared/components/tires/tires-add/tires-add.component";
import {WindowsComponent} from "../carly-shared/components/windows/windows.component";
import {WindowsAddComponent} from "../carly-shared/components/windows/windows-add/windows-add.component";
import {PaintingComponent} from "../carly-shared/components/painting/painting.component";
import {PaintingAddComponent} from "../carly-shared/components/painting/painting-add/painting-add.component";
import {WheelsEditComponent} from "../carly-shared/components/wheels/wheels-edit/wheels-edit.component";
import {SideMenuComponent} from "../carly-shared/components/side-menu/side-menu.component";
import {TiresEditComponent} from "../carly-shared/components/tires/tires-edit/tires-edit.component";
// import {OrdersComponent} from "../carly-shared/components/orders/orders.component";
// import {ModelsComponent} from "../carly-shared/components/models/models.component";
// import {ModelsAddComponent} from "../carly-shared/components/models/models-add/models-add.component";

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
              {path: 'add', component: WheelsAddComponent},
              {path: 'detail/:id', component: WheelsEditComponent},
            ]
          },
          {
            path: 'breaks',
            children: [
              {path: '', component: BreaksComponent},
              {path: 'add', component: BreaksAddComponent}
            ]
          },
          {
            path: 'tires',
            children: [
              {path: '', component: TiresComponent},
              {path: 'add', component: TiresAddComponent},
              {path: 'detail/:id', component: TiresEditComponent}
            ]
          },
          {
            path: 'windows',
            children: [
              {path: '', component: WindowsComponent},
              {path: 'add', component: WindowsAddComponent}
            ]
          },
          {
            path: 'painting',
            children: [
              {path: '', component: PaintingComponent},
              {path: 'add', component: PaintingAddComponent}
            ]
          },
          {
            path: 'equipment',
            children: [
              {path: '', component: EquipmentComponent},
              {path: 'add', component: EquipmentAddComponent}
            ]
          }
        ]
      },
      // {
      //   path: 'models',
      //   children: [
      //     {path: '', component: ModelsComponent},
      //     {path: 'add', component: ModelsAddComponent}
      //   ]
      // },
      // {
      //   path: 'orders',
      //   children: [
      //     {path: '', component: OrdersComponent}
      //   ]
      // },
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
