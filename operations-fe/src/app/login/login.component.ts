import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../carly-shared/services/authentication.service";
import {Login} from "../carly-shared/model/login.model";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";
  invalidLogin = false;

  login: Login;

  constructor(
    private router: Router,
    private loginService: AuthenticationService
  ) {
  }

  ngOnInit() {
  }

  checkLogin() {
    // this.loginService.authenticate().subscribe()
  }


}
