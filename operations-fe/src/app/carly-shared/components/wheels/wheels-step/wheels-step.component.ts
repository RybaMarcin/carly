import {Component, Input, OnInit} from '@angular/core';
import {Wheels} from "../../../model/wheels.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {wheelsDetailsFormFields, wheelsPreviews} from "../wheels-form/wheels-form-fields";
import {WheelsManagementService} from "../../../resources/wheels-management.service";

@Component({
  selector: 'wheels-step',
  templateUrl: './wheels-step.component.html',
  styleUrls: ['./wheels-step.component.scss']
})
export class WheelsStepComponent implements OnInit {

  public wheels: Wheels.Model;

  wheelsStepDetailsControls = this.fgService.addControlToModel(wheelsDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = wheelsPreviews;
      }
      return controlModel;
    });

  gridColumns = 4;

  allWheels: Wheels.Model[];

  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private wheelsService: WheelsManagementService
  ) { }

  ngOnInit() {

    this.wheelsService.getAllWheels().subscribe(data => {
        this.allWheels = data;
        this.wheels = data[0];
      },
      error => console.log(error)
    );

  }

  getCurrentWheels($event) {
    this.wheels = $event;
    console.log(200, this.wheels);
  }


}
