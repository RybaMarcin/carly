import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {ValueLabel} from "../../../model/value-label";
import {Validators} from "@angular/forms";


const temporaryUserEmails = function(): Array<ValueLabel> {

  const emails = [
      'carly-user@gmail.com',
      'carly-user2@gmail.com',
      'carly-user3@gmail.com',
      'carly-user4@gmail.com',
      'carly-user5@gmail.com',
      'carly-user6@gmail.com',
      'carly-user7@gmail.com',
      'carly-user8@gmail.com',
      'carly-user9@gmail.com',
      'carly-user10@gmail.com',
      'carly-user11@gmail.com',
      'carly-user12@gmail.com',
      'carly-user13@gmail.com',
      'carly-user14@gmail.com',
      'carly-user15@gmail.com',
  ];

  return emails.map(email => ({label: email, value: email}));

};


export const companyDetailsFormFields: FormGroupHelper.Model[] = [
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
      inputName: 'email',
      label: 'Email address',
      validators: [Validators.required, Validators.pattern('')],
      type: 'text',
      rows: 1,
      cols: 4,
      errors: [
        {
          message: 'Please enter valid e-mail.',
          validator: 'pattern'
        }
      ]
    },
    {
      inputName: 'phoneNumber',
      label: 'Phone number',
      validators: [Validators.required],
      type: 'text',
      rows: 1,
      cols: 4,
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
      inputName: 'firstLine',
      label: 'First line',
      validators: [Validators.required],
      type: 'text',
      rows: 1,
      cols: 4
    },
    {
      inputName: 'secondLine',
      label: 'Second line',
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
