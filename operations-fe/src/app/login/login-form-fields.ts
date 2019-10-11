import {FormGroupHelper} from "../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const loginFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'login',
    label: 'Email',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'password',
    label: 'Password',
    validators: [Validators.required],
    type: 'password',
    cols: 4,
    rows: 1
  }
];
