import { Component, OnInit } from '@angular/core';
import {NavLink} from "../../model/nav-links";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent {

  navLinks: Array<NavLink> =[];

  constructor(
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.data.subscribe(data => this.navLinks = data.navLinks);
  }


}
