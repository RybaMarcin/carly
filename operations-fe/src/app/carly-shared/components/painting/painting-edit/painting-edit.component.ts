import {Component, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "../../../services/message.service";
import {map, mergeMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Painting} from "../../../model/painting.model";
import {PaintingManagementService} from "../../../resources/painting-management.service";

@Component({
  selector: 'painting-edit',
  templateUrl: './painting-edit.component.html',
  styleUrls: ['./painting-edit.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class PaintingEditComponent implements OnInit {

  private painting: Painting.Model;
  private formAction = PartFormAction.EDIT;


  constructor(
    private activatedRoute: ActivatedRoute,
    private paintingService: PaintingManagementService,
    private router: Router,
    private messageService: MessageService

  ) {
  }

  ngOnInit() {

    this.findPaintingById()
      .pipe(
        mergeMap(id => this.paintingService.findPaintingById(id)),
        map(model => {
          console.log(200, model);
          return model;
        })
      ).subscribe(model => {
      this.painting = model;
    });

  }

  private findPaintingById(): Observable<string> {
    console.log(100);
    return this.activatedRoute.params.pipe(map(params => params.id));
  }

}
