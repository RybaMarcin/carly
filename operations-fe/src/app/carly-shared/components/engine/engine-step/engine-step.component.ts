import { Component, OnInit } from '@angular/core';
import {Engine} from "../../../model/engine.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {EngineManagementService} from "../../../resources/engine-management.service";
import {engineDetailsFormFields, enginePreviews} from "../engine-form/engine-form-fields";
import {Wheels} from "../../../model/wheels.model";

@Component({
  selector: 'engine-step',
  templateUrl: './engine-step.component.html',
  styleUrls: ['./engine-step.component.scss']
})
export class EngineStepComponent implements OnInit {

  public engine: Engine.Model;

  engineStepDetailsControls = this.fgService.addControlToModel(engineDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = enginePreviews;
      }
      return controlModel;
    });

  gridColumns = 4;

  allEngines: Engine.Model[];

  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private engineService: EngineManagementService
  ) {
  }

  ngOnInit() {

    this.engineService.getAllEngines().subscribe(data => {
      this.allEngines = data;
      this.engine = data[0];
    });


  }

  getCurrentEngine($event) {
    this.engine = $event;
    console.log(100, this.engine);
  }



}
