import {Component, OnInit} from '@angular/core';
import {Login} from "../carly-shared/model/login.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {loginFormFields} from "./login-form-fields";
import {Router} from "@angular/router";
import {AuthenticationService} from "../carly-shared/services/authentication.service";
import {MatDialog} from "@angular/material/dialog";
import {FormGroupHelperService} from "../carly-shared/services/form-group-helper.service";
import {User} from "../carly-shared/model/user.model";
import {RegistrationComponent} from "../registration/registration.component";
import {ResetPasswordComponent} from "../reset-password/reset-password.component";
import {LocalStorageService} from "angular-2-local-storage";
import {UserManagementService} from "../carly-shared/resources/user-management.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  invalidLogin = false;

  login: Login;

  generalForm: FormGroup;

  loginDetailsForm: FormGroup;
  loginDetailsFormControls = this.fgService.addControlToModel(loginFormFields);

  gridColumns = 4;

  constructor(
    private router: Router,
    private loginService: AuthenticationService,
    private dialog: MatDialog,
    private resetDialog: MatDialog,
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private storageService: LocalStorageService,
    private userService: UserManagementService
  ) {
  }

  ngOnInit() {

    this.loginDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.loginDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      loginDetailsForm: this.loginDetailsForm
    });
  }

  checkLogin() {

    const login: User = {

      ...this.loginDetailsForm.value
    };

    this.loginService.authenticate(login).subscribe(userContext => {
        console.log(userContext);
        this.userService.cacheUserContext(userContext);
      },
      error => {
        console.log(error)
      });
  }


  openRegistrationDialog() {
    this.dialog.open(RegistrationComponent);
  }


  openForgetPasswordDialog() {
    this.resetDialog.open(ResetPasswordComponent);
  }


}
