import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const customerDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'firstName',
    label: 'First name',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
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
    inputName: 'phoneNumber',
    label: 'Phone number',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'email',
    label: 'E-mail',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'gender',
    label: 'Gender',
    validators: [Validators.required],
    type: '',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'createAt',
    label: 'Create at',
    validators: [Validators.required],
    type: 'date',
    cols: 1,
    rows: 1
  }
];

export const addressDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'street',
    label: 'Street',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'number',
    label: 'Number',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'flat',
    label: 'Flat',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'town',
    label: 'Town',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'zipCode',
    label: 'Zip code',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'country',
    label: 'Country',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  }
];
