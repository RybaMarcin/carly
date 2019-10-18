import {FormGroupHelper} from "../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const resetPasswordFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'email',
    label: 'E-mail',
    validators: [Validators.required, Validators.pattern('')],
    type: 'text',
    cols: 4,
    rows: 1,
    errors: [
      {
        message: 'Please provide e-mail address!',
        validator: 'required'
      },
      {
        message: 'Please provide valid email!',
        validator: 'pattern'
      }
    ]
  },
  {
    inputName: 'password',
    label: 'Password',
    validators: [Validators.required],
    type: 'password',
    cols: 4,
    rows: 1,
    errors: [
      {
        message: 'Please provide password!',
        validator: 'required'
      }
    ]
  },
  {
    inputName: 'matchingPassword',
    label: 'Repeat password',
    validators: [Validators.required],
    type: 'password',
    cols: 4,
    rows: 1,
    errors: [
      {
        message: 'Please repeat password!',
        validator: 'required'
      }
    ]
  }
];
