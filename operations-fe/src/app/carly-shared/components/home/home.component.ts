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

  homeInformationMap = new Map<number, Home>();

  homePageInformation: Home;

  counter: number;
  maxCounter: number;

  constructor(
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {

    this.counter = 0;

    console.log(200, homeObjects);

    this.homeInformation = homeObjects;
    this.maxCounter = this.homeInformation.length - 1;
    console.log(100, this.counter);
    console.log(110, this.homeInformation);


    this.homePageInformation = this.homeInformation[0];

    this.createHomePageInformationMap(this.homeInformation);

  }

  openLoginPage() {
    this.dialog.open(LoginComponent);
  }

  next() {
    console.log(300, this.counter);
    if(this.counter == this.maxCounter) {
      return;
    }
    this.counter++;
  }

  previous() {
    console.log(300, this.counter);
    if(this.counter == 0) {
      return;
    }
    this.counter--;
  }


  createHomePageInformationMap(list: Array<Home>) {
    for(let i = 0; i < list.length; i++) {
      this.homeInformationMap.set(i, list[i]);
    }
  }


  getHomePageInformation(): Home {
    return this.homeInformationMap.get(this.counter);
  }

}
