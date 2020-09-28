import { Component, OnInit } from '@angular/core';
import {Wheels} from "../../../model/wheels.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {ActivatedRoute, Router} from "@angular/router";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {MessageService} from "../../../services/message.service";
import {map, mergeMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Breaks} from "../../../model/breaks.model";
import {BreaksManagementService} from "../../../resources/breaks-management.service";

@Component({
  selector: 'breaks-edit',
  templateUrl: './breaks-edit.component.html',
  styleUrls: ['./breaks-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class BreaksEditComponent implements OnInit {

  private breaks: Breaks.Model;
  private formAction = PartFormAction.EDIT;


  constructor(
    private activatedRoute: ActivatedRoute,
    private breaksService: BreaksManagementService,
    private router: Router,
    private messageService: MessageService

  ) {
  }

  ngOnInit() {

    this.findBreaksById()
      .pipe(
        mergeMap(id => this.breaksService.findWheelsById(id)),
        map(model => {
          console.log(200, model);
          return model;
        })
      ).subscribe(model => {
      this.breaks = model;
    });

  }

  private findBreaksById(): Observable<string> {
    console.log(100);
    return this.activatedRoute.params.pipe(map(params => params.id));
  }

}
