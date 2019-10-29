import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FilterBarComponent} from "./components/filter-bar/filter-bar.component";
import {FileUploaderComponent} from "./components/file-uploader/file-uploader.component";
import {DetailMenuComponent} from "./components/detail-menu/detail-menu.component";
import {Ng5SliderModule} from "ng5-slider";
import {FileInputAccessorModule} from "file-input-accessor";
import {CarsDashboardComponent} from "./components/cars/cars-dashboard.component";
import {CarsAddComponent} from "../carly-company/components/cars/cars-add/cars-add.component";
import { CarEditComponent } from './components/cars/car-edit/car-edit.component';
import { CarFormComponent } from './components/cars/car-form/car-form.component';
import { CarAcceptanceComponent } from './components/cars/car-acceptance/car-acceptance.component';
import { CompanyFormComponent } from './components/company/company-form/company-form.component';
import { CompanyAcceptanceComponent } from './components/company/company-acceptance/company-acceptance.component';
import {CompanyEditComponent} from "./components/company/company-edit/company-edit.component";
import { CustomerEditComponent } from './components/customers/customer-edit/customer-edit.component';
import { CustomerFormComponent } from './components/customers/customer-form/customer-form.component';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from "ng-pick-datetime";
import {BreakpointService} from "./services/breakpoint.service";
import {CompanyContextService} from "./services/company-context.service";
import {MessageService} from "./services/message.service";
import {UserManagementService} from "./resources/user-management.service";
import {FormGroupHelperService} from "./services/form-group-helper.service";
import {OperationsService} from "./resources/operations.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {DateAdapter, MAT_DATE_FORMATS} from "@angular/material";
import {CARLY_DATE_FORMATS, CarlyDateAdapter} from "./utils/date-formatter";
import { SuffixPipe } from './utils/suffix.pipe';
import {CompanyContextComponent} from "./components/company-context/company-context.component";
import {UserCanAccessDirective} from "./directives/user-can-access.directive";
import {FormGroupComponent} from "./components/form-group/form-group.component";
import {CarlyMatModule} from "./modules/carly-mat.module";
import {ImgLoaderComponent} from "./components/img-loader/img-loader.component";
import { ForInPipe } from './services/for-in.pipe';
import { SecureUriPipe } from './services/secure-uri.pipe';
import {DateISO8601Pipe, DateTimeISO8601Pipe} from "./services/date.pipe";
import {FileSizeModule} from "ngx-filesize";
import {AuthInterceptor} from "./services/auth-interceptor";
import {PartsDashboardComponent} from "./components/parts/parts-dashboard.component";
import {CompanyContextGuard} from "./services/guards/company-context.guard";
import {CompanyManagementService} from "./resources/company-management.service";
import { AuthenticationGuard } from './services/guards/authentication.guard';
import {CarManagementService} from "./resources/car-management.service";
import {PartsComponent} from "../carly-company/components/parts/parts.component";
import { WheelsComponent } from './components/wheels/wheels.component';
import { EngineEditComponent } from './components/engine/engine-edit/engine-edit.component';
import { EngineFormComponent } from './components/engine/engine-form/engine-form.component';
import { WheelsEditComponent } from './components/wheels/wheels-edit/wheels-edit.component';
import { WheelsFormComponent } from './components/wheels/wheels-form/wheels-form.component';
import { EnginesComponent } from './components/engine/engines.component';
import { EngineAddComponent } from './components/engine/engine-add/engine-add.component';
import { WheelsAddComponent } from './components/wheels/wheels-add/wheels-add.component';
import { BreaksComponent } from './components/breaks/breaks.component';
import { BreaksAddComponent } from './components/breaks/breaks-add/breaks-add.component';
import { BreaksEditComponent } from './components/breaks/breaks-edit/breaks-edit.component';
import { BreaksFormComponent } from './components/breaks/breaks-form/breaks-form.component';
import { SideMenuComponent } from './components/side-menu/side-menu.component';
import { CustomersComponent } from './components/customers/customers.component';
import { HomeComponent } from './components/home/home.component';
import { EquipmentComponent } from './components/equipment/equipment.component';
import { EquipmentAddComponent } from './components/equipment/equipment-add/equipment-add.component';
import { EquipmentFormComponent } from './components/equipment/equipment-form/equipment-form.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { WindowsComponent } from './components/windows/windows.component';
import { WindowsAddComponent } from './components/windows/windows-add/windows-add.component';
import { WindowsEditComponent } from './components/windows/windows-edit/windows-edit.component';
import { WindowsFormComponent } from './components/windows/windows-form/windows-form.component';
import { PaintingComponent } from './components/painting/painting.component';
import { PaintingAddComponent } from './components/painting/painting-add/painting-add.component';
import { PaintingEditComponent } from './components/painting/painting-edit/painting-edit.component';
import { PaintingFormComponent } from './components/painting/painting-form/painting-form.component';
import { TiresComponent } from './components/tires/tires.component';
import { TiresAddComponent } from './components/tires/tires-add/tires-add.component';
import { TiresEditComponent } from './components/tires/tires-edit/tires-edit.component';
import { TiresFormComponent } from './components/tires/tires-form/tires-form.component';
import { WheelsStepComponent } from './components/wheels/wheels-step/wheels-step.component';
import { EngineStepComponent } from './components/engine/engine-step/engine-step.component';
import { BreaksStepComponent } from './components/breaks/breaks-step/breaks-step.component';
import { PartStepComponent } from './components/parts/part-step/part-step.component';
import { PartFormComponent } from './components/parts/part-form/part-form.component';
import { TiresStepComponent } from './components/tires/tires-step/tires-step.component';
import { WindowsStepComponent } from './components/windows/windows-step/windows-step.component';
import { EquipmentStepComponent } from './components/equipment/equipment-step/equipment-step.component';
import { SideNavBarComponent } from './components/side-nav-bar/side-nav-bar.component';
import { EquipmentEditComponent } from './components/equipment/equipment-edit/equipment-edit.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    Ng5SliderModule,
    FormsModule,
    ReactiveFormsModule,
    FileInputAccessorModule,
    FileSizeModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    CarlyMatModule
  ],
  declarations: [
    FilterBarComponent,
    FileUploaderComponent,
    DetailMenuComponent,
    CarsDashboardComponent,
    CarsAddComponent,
    CarEditComponent,
    CarFormComponent,
    CarAcceptanceComponent,
    CompanyFormComponent,
    CompanyAcceptanceComponent,
    CompanyEditComponent,
    CustomerEditComponent,
    CustomerFormComponent,
    SuffixPipe,
    ForInPipe,
    SecureUriPipe,
    PartsDashboardComponent,
    PartsComponent,
    CompanyContextComponent,
    UserCanAccessDirective,
    FormGroupComponent,
    ImgLoaderComponent,
    DateISO8601Pipe,
    DateTimeISO8601Pipe,
    WheelsComponent,
    EngineEditComponent,
    EngineFormComponent,
    WheelsEditComponent,
    WheelsFormComponent,
    EnginesComponent,
    EngineAddComponent,
    WheelsAddComponent,
    BreaksComponent,
    BreaksAddComponent,
    BreaksEditComponent,
    BreaksFormComponent,
    SideMenuComponent,
    CustomersComponent,
    HomeComponent,
    EquipmentComponent,
    EquipmentAddComponent,
    EquipmentFormComponent,
    NotificationsComponent,
    WindowsComponent,
    WindowsAddComponent,
    WindowsEditComponent,
    WindowsFormComponent,
    PaintingComponent,
    PaintingAddComponent,
    PaintingEditComponent,
    PaintingFormComponent,
    TiresComponent,
    TiresAddComponent,
    TiresEditComponent,
    TiresFormComponent,
    WheelsStepComponent,
    EngineStepComponent,
    BreaksStepComponent,
    PartStepComponent,
    PartFormComponent,
    TiresStepComponent,
    WindowsStepComponent,
    EquipmentStepComponent,
    SideNavBarComponent,
    EquipmentEditComponent,
  ],
  providers: [
    BreakpointService,
    CompanyContextService,
    MessageService,
    UserManagementService,
    FormGroupHelperService,
    OperationsService,
    CompanyContextGuard,
    CompanyManagementService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: DateAdapter,
      useClass: CarlyDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS,
      useValue: CARLY_DATE_FORMATS
    },
    CarManagementService,
    AuthenticationGuard
  ],
  entryComponents: [

  ],
  exports: [
    CarlyMatModule,
    Ng5SliderModule,
    FilterBarComponent,
    FileUploaderComponent,
    DetailMenuComponent,
    CompanyContextComponent,
    CarEditComponent,
    CarFormComponent,
    CompanyEditComponent,
    CompanyFormComponent,
    UserCanAccessDirective,
    SuffixPipe,
    FormGroupComponent,
    ImgLoaderComponent,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ForInPipe,
    SecureUriPipe,
    DateISO8601Pipe,
    DateTimeISO8601Pipe
  ]
})
export class CarlySharedModule { }
