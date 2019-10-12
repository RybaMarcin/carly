import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../../login/login.component";

@Component({
  selector: 'registration-confirmation',
  templateUrl: './registration-confirmation.component.html',
  styleUrls: ['./registration-confirmation.component.scss']
})
export class RegistrationConfirmationComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
  }

  openLoginDialog() {
    this.dialog.open(LoginComponent);
  }

}
