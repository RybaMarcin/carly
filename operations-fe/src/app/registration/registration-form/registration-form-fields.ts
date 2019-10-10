import {FormGroupHelper} from "../../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const registrationDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1,
    errors: [
      {
        message: 'Please provide name!',
        validator: 'required'
      }
    ]
  },
  {
    inputName: 'lastName',
    label: 'Last name',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'email',
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
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'matchingPassword',
    label: 'Repeat password',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
];
