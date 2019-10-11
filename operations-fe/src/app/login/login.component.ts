import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../carly-shared/services/authentication.service";
import {Login} from "../carly-shared/model/login.model";
import {User} from "../carly-shared/model/user.model";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../registration/registration.component";

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

  constructor(
    private router: Router,
    private loginService: AuthenticationService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
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
