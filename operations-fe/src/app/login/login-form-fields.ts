import {FormGroupHelper} from "../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const loginFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'email',
    label: 'Email',
    validators: [Validators.required, Validators.pattern('')],
    type: 'text',
    cols: 4,
    rows: 1,
    errors: [
      {
        message: 'Please enter e-mail to login!',
        validator: 'required'
      },
      {
        message: 'Please enter valid e-mail.',
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
        message: 'Please enter password!',
        validator: 'required'
      }
    ]
  }
];
