import { Component, OnInit } from '@angular/core';
import {Wheels} from "../../../model/wheels.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {ActivatedRoute, Router} from "@angular/router";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {MessageService} from "../../../services/message.service";
import {map, mergeMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Engine} from "../../../model/engine.model";
import {EngineManagementService} from "../../../resources/engine-management.service";

@Component({
  selector: 'app-engine-edit',
  templateUrl: './engine-edit.component.html',
  styleUrls: ['./engine-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class EngineEditComponent implements OnInit {

  private engine: Engine.Model;
  private formAction = PartFormAction.EDIT;


  constructor(
    private activatedRoute: ActivatedRoute,
    private engineService: EngineManagementService,
    private router: Router,
    private messageService: MessageService

  ) {
  }

  ngOnInit() {

    this.findEngineById()
      .pipe(
        mergeMap(id => this.engineService.findEngineById(id)),
        map(model => {
          console.log(200, model);
          return model;
        })
      ).subscribe(model => {
      this.engine = model;
    });

  }


  private findEngineById(): Observable<string> {
    console.log(100);
    return this.activatedRoute.params.pipe(map(params => params.id));
  }

}
