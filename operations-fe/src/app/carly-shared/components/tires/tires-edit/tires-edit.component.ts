import { Component, OnInit } from '@angular/core';
import {Tires} from "../../../model/tires.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "../../../services/message.service";
import {Observable} from "rxjs";
import {map, mergeMap} from "rxjs/operators";
import {TiresManagementService} from "../../../resources/tires-management.service";

@Component({
  selector: 'tires-edit',
  templateUrl: './tires-edit.component.html',
  styleUrls: ['./tires-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class TiresEditComponent implements OnInit {

  private tires: Tires.Model;
  private formAction = PartFormAction.EDIT;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessageService,
    private tiresService: TiresManagementService
  ) { }

  ngOnInit() {

    this.findTiresById()
      .pipe(
        mergeMap(id => this.tiresService.findTiresById(id)),
        map(model => {
          return model;
        })
      ).subscribe(model => {
        this.tires = model;
    })

  }


  private findTiresById(): Observable<string> {
    return this.activatedRoute.params.pipe(map(params => params.id));
  }


}
