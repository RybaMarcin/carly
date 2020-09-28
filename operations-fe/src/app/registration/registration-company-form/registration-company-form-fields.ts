import {FormGroupHelper} from "../../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const registrationCompanyDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Company name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4,
  },
  {
    inputName: 'brand',
    label: 'Brand name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4,
  },
  {
    inputName: 'number',
    label: 'Company number',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4,
  },
  {
    inputName: 'phoneNumber',
    label: 'Phone number',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4,
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
  }
];

export const addressDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'city',
    label: 'City',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'country',
    label: 'Country',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'street',
    label: 'Street',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'number',
    label: 'Number',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'zipCode',
    label: 'Zip code',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
];
