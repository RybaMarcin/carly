import { Component, OnInit } from '@angular/core';
import {NavLink} from "../../model/nav-links";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-detail-menu',
  templateUrl: './detail-menu.component.html',
  styleUrls: ['./detail-menu.component.scss']
})
export class DetailMenuComponent {

  navLinks: Array<NavLink> = [];

  constructor(
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.data.subscribe(data => this.navLinks = data.navLinks);
  }


}
