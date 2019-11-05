import {FormGroupHelper} from "../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const customerFilterFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'actionDateLowerLimit',
    label: 'Change date from',
    validators: [Validators.required],
    type: 'date'
  },
  {
    inputName: 'actionDateUpperLimit',
    label: 'Change date to',
    validators: [Validators.required],
    type: 'date'
  }
];
