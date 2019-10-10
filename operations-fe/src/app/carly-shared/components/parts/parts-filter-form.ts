import {FormGroupHelper} from "../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const partsFilterFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: 'date'
  },
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: 'date'
  },
];
