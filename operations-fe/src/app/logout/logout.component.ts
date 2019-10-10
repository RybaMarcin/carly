import { Component, OnInit } from '@angular/core';
import {LocalStorageService} from "angular-2-local-storage";
import {OperationsService} from "../carly-shared/resources/operations.service";

declare const JWT_TOKEN;

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
})
export class LogoutComponent implements OnInit {

  constructor(
    private storageService: LocalStorageService,
    private operationsService: OperationsService
  ) {
  }

  ngOnInit() {
    this.storageService.clearAll();

    if (JWT_TOKEN) {
      this.operationsService.logout().subscribe(uri => window.location.href = uri);
    } else {
      window.location.replace('/');
    }
  }

}
