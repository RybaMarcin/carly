import {Component, OnInit} from '@angular/core';
import {User} from "./carly-shared/model/user.model";
import {Roles} from "./carly-shared/model/roles.model";
import {ActivatedRoute} from "@angular/router";
import {LocalStorageService} from 'angular-2-local-storage';
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "./login/login.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  user: User;
  company: any;

  CarlyOperator: User["role"] = Roles.CARLY_OPERATOR;
  CarlyCompany: User["role"] = Roles.CARLY_COMPANY;

  notificationIsActive: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private storageService: LocalStorageService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    // this.activatedRoute.queryParams.subscribe(data => {
    //   const {companyContext} = data;
    //   this.user = this.storageService.get('userContext');
    //   this.company = this.storageService.get('companyContext');
    //
    //   if (companyContext || this.user.role === Roles.CARLY_OPERATOR || this.company) {
    //     this.notificationIsActive = true;
    //   }
    // });
  }

  openLoginDialog() {
    this.dialog.open(LoginComponent);
  }

}
