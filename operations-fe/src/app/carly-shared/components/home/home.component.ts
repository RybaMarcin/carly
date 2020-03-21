import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../../../login/login.component";
import {homeObjects} from "./home-object";
import {Home} from "../../model/home.model";

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  homeInformation: Array<Home>;


  constructor(
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.homeInformation = homeObjects;
  }

  openLoginPage() {
    this.dialog.open(LoginComponent);
  }


}
