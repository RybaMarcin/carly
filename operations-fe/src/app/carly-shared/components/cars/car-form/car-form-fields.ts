import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {Transmission} from "../../../model/transmission.enum";
import {Equipment} from "../../../model/equipment.model";
import {ValueLabel} from "../../../model/value-label";

export const carDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'model',
    label: 'Model',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'carBody',
    label: 'Car body',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'maxSpeed',
    label: 'Max speed',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'accelerate',
    label: 'Accelerate',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'transmission',
    label: 'Transmission',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 4,
    selectOptions: [...Object.values(Transmission).map(value => ({label: value, value}))]
  },
  {
    inputName: 'bodyPainting',
    label: 'Body painting',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'equipment',
    label: 'Equipment',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: [],
    selectMultiple: true
  },
];


export const carTypes: Array<ValueLabel> = [
  {
    value: 'body_1.png',
    label: 'Kombi'
  },
  {
    value: 'body_2.png',
    label: 'Sedan'
  },
  {
    value: 'body_3.png',
    label: 'Hatchback'
  },
  {
    value: '',
    label: 'Compact'
  },
  {
    value: '',
    label: 'SUV'
  },
  {
    value: '',
    label: 'Minivan'
  },
  {
    value: '',
    label: 'Cabriolet'
  },
  {
    value: '',
    label: 'Coupe'
  },
  {
    value: '',
    label: 'Small car'
  },
  {
    value: '',
    label: 'City car'
  }
];
