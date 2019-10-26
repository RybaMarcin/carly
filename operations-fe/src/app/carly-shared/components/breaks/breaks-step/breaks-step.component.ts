import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {BreaksManagementService} from "../../../resources/breaks-management.service";
import {Breaks} from "../../../model/breaks.model";
import {breaksDetailsFormFields, breaksPreviews} from "../breaks-form/breaks-form-fields";

@Component({
  selector: 'breaks-step',
  templateUrl: './breaks-step.component.html',
  styleUrls: ['./breaks-step.component.scss']
})
export class BreaksStepComponent implements OnInit {

  public breaks: Breaks.Model;

  breaksStepDetailsControls = this.fgService.addControlToModel(breaksDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = breaksPreviews;
      }
      return controlModel;
});

  gridColumns = 4;

  allBreaks: Breaks.Model[];

  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private breaksService: BreaksManagementService
  ) { }

  ngOnInit() {

    this.breaksService.getAllBreaks().subscribe(data => {
        this.allBreaks = data;
        this.breaks = data[0];
      },
      error => console.log(error)
    );

  }

  getCurrentBreaks($event) {
    this.breaks = $event;
    console.log(300, this.breaks);
  }

}
