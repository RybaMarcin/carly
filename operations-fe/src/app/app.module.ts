import {APP_INITIALIZER, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CarlySharedModule} from "./carly-shared/carly-shared.module";
import {CarlyMatModule} from "./carly-shared/modules/carly-mat.module";
import {Ng5SliderModule} from "ng5-slider";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LogoutComponent} from "./logout/logout.component";
import {LocalStorageModule} from "angular-2-local-storage";
import {AppRoutingModule} from "./app.routing";
import {UserManagementService} from "./carly-shared/resources/user-management.service";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationFormComponent } from './registration/registration-form/registration-form.component';
import { RegistrationConfirmationComponent } from './registration/registration-confirmation/registration-confirmation.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResetPasswordConfirmationComponent } from './reset-password/reset-password-confirmation/reset-password-confirmation.component';

// const userContextFactory = (userService: UserManagementService) => {
//   return () => userService.getUserContext();
// };

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CarlySharedModule,
    CarlyMatModule,
    HttpClientModule,
    RouterModule,
    LocalStorageModule.withConfig({
      prefix: 'carly-app',
      storageType: 'sessionStorage'
    }),
  ],
  declarations: [
    AppComponent,
    LogoutComponent,
    LoginComponent,
    RegistrationComponent,
    RegistrationFormComponent,
    RegistrationConfirmationComponent,
    ResetPasswordComponent,
    ResetPasswordConfirmationComponent
  ],
  providers: [
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: userContextFactory,
    //   deps: [UserManagementService],
    //   multi: true
    // }
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    CarlySharedModule,
    CarlyMatModule,
    Ng5SliderModule,
    HttpClientModule,
    RouterModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
