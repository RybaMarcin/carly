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

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {



  email = "";
  password = "";
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
    private fgService: FormGroupHelperService
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
    // console.log(400, this.email);
    // console.log(410, this.password);

    const login: User = {
      // email: this.email,
      // password: this.password
      ...this.loginDetailsForm.value
    };

    this.loginService.authenticate(login).subscribe(data => {
        console.log(data);
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
