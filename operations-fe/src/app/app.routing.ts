import {RouterModule, Routes} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {LogoutComponent} from "./logout/logout.component";
import {AuthenticationGuard} from "./carly-shared/services/guards/authentication.guard";
import {CompanyContextGuard} from "./carly-shared/services/guards/company-context.guard";
import {CarlyRoleGuard} from "./carly-shared/services/guards/carly-role.guard";
import {Roles} from "./carly-shared/model/roles.model";
import {CarlyMatcher} from "./carly-shared/services/matcher";
import {CarlyOperatorComponent} from "./carly-operator/carly-operator.component";
import {DashboardComponent} from "./carly-operator/components/dashboard/dashboard.component";
import {CompaniesComponent} from "./carly-operator/components/companies/companies.component";
import {CompanyAddComponent} from "./carly-operator/components/companies/company-add/company-add.component";
import {DetailMenuComponent} from "./carly-shared/components/detail-menu/detail-menu.component";
import {CompanyEditComponent} from "./carly-shared/components/company/company-edit/company-edit.component";
import {CarsDashboardComponent} from "./carly-shared/components/cars/cars-dashboard.component";
import {DataType} from "./carly-shared/model/data-type.enum";
import {PartsDashboardComponent} from "./carly-shared/components/parts/parts-dashboard.component";
import {ContractsMatchingComponent} from "./carly-operator/components/contracts-matching/contracts-matching.component";
import {CarsComponent} from "./carly-operator/components/cars/cars.component";
import {CarsAddComponent} from "./carly-company/components/cars/cars-add/cars-add.component";
import {CarEditComponent} from "./carly-shared/components/cars/car-edit/car-edit.component";
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {RegistrationConfirmationComponent} from "./registration/registration-confirmation/registration-confirmation.component";


const ROUTES: Routes = [

  {path: 'logout', component: LogoutComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'confirmation', component: RegistrationConfirmationComponent},
  //TODO: Uncomment paths below when authentication will be finished!

  // {
  //   path: '',
  //   canActivate: [AuthenticationGuard, CompanyContextGuard],
  //   canActivateChild: [AuthenticationGuard, CompanyContextGuard],
  //   children: [
  //     {
  //       canActivate: [CarlyRoleGuard],
  //       data: {roles: [Roles.CARLY_OPERATOR]},
  //       matcher: CarlyMatcher.carlyOperatorMatcher,
  //       loadChildren: 'src/app/carly-operator/carly-operator.module#CarlyOperatorModule'
  //     },
  //     {
  //       canActivate: [CarlyRoleGuard],
  //       data: {roles: [Roles.CARLY_COMPANY]},
  //       matcher: CarlyMatcher.carlyCompanyMatcher,
  //       loadChildren: 'src/app/carly-operator.module#CarlyCompanyModule'
  //     }
  //   ]
  // }

  //TODO: Replace path below when authentication will be finished!

  {path: '', loadChildren: 'src/app/carly-operator/carly-operator.module#CarlyOperatorModule'}

];


export const AppRoutingModule: ModuleWithProviders = RouterModule.forRoot(ROUTES, {useHash: true});
