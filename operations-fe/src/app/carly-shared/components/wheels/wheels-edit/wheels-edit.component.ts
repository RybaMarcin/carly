import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {MessageService} from "../../../services/message.service";
import {Observable} from "rxjs";
import {map, mergeMap} from "rxjs/operators";
import {Wheels} from "../../../model/wheels.model";
import {PartFormAction} from "../../../model/part-form-action.model";

@Component({
  selector: 'wheels-edit',
  templateUrl: './wheels-edit.component.html',
  styleUrls: ['./wheels-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class WheelsEditComponent implements OnInit {


  private wheels: Wheels.Model;
  private formAction = PartFormAction.EDIT;


  constructor(
    private activatedRoute: ActivatedRoute,
    private wheelsService: WheelsManagementService,
    private router: Router,
    private messageService: MessageService

  ) {
  }

  ngOnInit() {

    this.findWheelsById()
      .pipe(
        mergeMap(id => this.wheelsService.findWheelsById(id)),
        map(model => {
          console.log(200, model);
          return model;
        })
      ).subscribe(model => {
        this.wheels = model;
    });

  }



  private findWheelsById(): Observable<string> {
    console.log(100);
    return this.activatedRoute.params.pipe(map(params => params.id));
  }


}
