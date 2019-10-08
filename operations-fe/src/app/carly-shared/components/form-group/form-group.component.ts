import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {FormGroupHelper} from "../../model/form-group-helper.model";

@Component({
  selector: 'form-group',
  templateUrl: './form-group.component.html',
  styleUrls: ['./form-group.component.scss']
})
export class FormGroupComponent {

  @Input() fg: FormGroup;
  @Input() gridColumns?: number = 4;
  @Input() name: string;
  @Input() controls: FormGroupHelper.ModelControl;
  @Output() click = new EventEmitter<string>();

  checkForErrors(fgName: string, fcName: string, error: FormGroupHelper.ErrorValidators, fcNestedName?: string) {
    const formGroup = this.fg.get(fgName);
    const formNestedControl = fcNestedName? formGroup.get(fcNestedName) : formGroup;
    const formControl = formNestedControl.get(fcName);

    return formControl.errors && formControl.errors[error.validator];
  }

  onClick(element: string) {
    this.click.emit(element);
    console.log(element);
  }



}
