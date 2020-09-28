import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";

@Component({
  selector: 'part-step',
  templateUrl: './part-step.component.html',
  styleUrls: ['./part-step.component.scss']
})
export class PartStepComponent implements OnInit {

  public part: any;
  @Input() partFields: FormGroupHelper.Model[];
  @Input() allParts: any;
  @Input() partStepDetailsControls: FormGroupHelper.ModelControl[];
  @Input() partGroup: string;
  @Output() currentPart = new EventEmitter();

  generalForm: FormGroup;

  partStepDetailsForm: FormGroup;

  counter: number;
  maxCounter: number;

  gridColumns = 4;

  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService
  ) {
  }

  ngOnInit() {

    this.part = this.allParts[0];

    this.counter = 0;
    this.maxCounter = this.allParts.length - 1;

    this.partStepDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.partStepDetailsControls)
    );

    this.generalForm = this.formBuilder.group({
      partStepDetailsForm: this.partStepDetailsForm
    });

    if(this.part) {
      this.setFormValue(this.part);
    }

    this.generalForm.disable();
    this.getCurrentPart();
  }

  setFormValue(part: any) {
    this.partStepDetailsControls
      .forEach(control => this.partStepDetailsForm
        .get(control.inputName)
        .setValue(part[control.inputName]));
  }

  next() {
    if(this.counter == this.maxCounter) {
      return;
    }
    this.counter++;
    this.part = this.allParts[this.counter];
    this.setFormValue(this.part);
    this.getCurrentPart();
  }

  previous() {
    if(this.counter == 0) {
      return;
    }
    this.counter--;
    this.part = this.allParts[this.counter];
    this.setFormValue(this.part);
    this.getCurrentPart();
  }

  getCurrentPart() {
    this.currentPart.emit(this.part);
  }


}
