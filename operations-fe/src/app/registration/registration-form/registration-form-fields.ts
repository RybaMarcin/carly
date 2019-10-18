import {FormGroupHelper} from "../../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {Registration} from "../../carly-shared/model/registration.model";

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
    rows: 1,
    errors: [
      {
        message: 'Please provide last name!',
        validator: 'required'
      }
    ]
  },
  {
    inputName: 'gender',
    label: 'Gender',
    validators: [Validators.required],
    type: 'select',
    cols: 4,
    rows: 1,
    selectOptions: [...Object.values(Registration.Gender).map(value => ({label: value, value}))]
  },
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
  },
];
