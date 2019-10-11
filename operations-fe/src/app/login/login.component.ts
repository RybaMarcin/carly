import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../carly-shared/services/authentication.service";
import {Login} from "../carly-shared/model/login.model";
import {User} from "../carly-shared/model/user.model";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../registration/registration.component";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../carly-shared/services/form-group-helper.service";
import {loginFormFields} from "./login-form-fields";

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

  constructor(
    private router: Router,
    private loginService: AuthenticationService,
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService
  ) {
  }

  ngOnInit() {

    this.loginDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.loginDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      loginDetails: this.loginDetailsForm
    });
  }

  checkLogin() {
    console.log(400, this.email);
    console.log(410, this.password);

    const login: User = {
      email: this.email,
      password: this.password
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


}
