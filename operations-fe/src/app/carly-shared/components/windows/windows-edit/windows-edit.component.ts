import { Component, OnInit } from '@angular/core';
import {Wheels} from "../../../model/wheels.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {ActivatedRoute, Router} from "@angular/router";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {MessageService} from "../../../services/message.service";
import {map, mergeMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Windows} from "../../../model/windows.model";
import {WindowsManagementService} from "../../../resources/windows-management.service";

@Component({
  selector: 'windows-edit',
  templateUrl: './windows-edit.component.html',
  styleUrls: ['./windows-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class WindowsEditComponent implements OnInit {

  private windows: Windows.Model;
  private formAction = PartFormAction.EDIT;


  constructor(
    private activatedRoute: ActivatedRoute,
    private windowsService: WindowsManagementService,
    private router: Router,
    private messageService: MessageService

  ) {
  }

  ngOnInit() {

    this.findWheelsById()
      .pipe(
        mergeMap(id => this.windowsService.findWindowsById(id)),
        map(model => {
          console.log(200, model);
          return model;
        })
      ).subscribe(model => {
      this.windows = model;
    });

  }

  private findWheelsById(): Observable<string> {
    console.log(100);
    return this.activatedRoute.params.pipe(map(params => params.id));
  }

}
