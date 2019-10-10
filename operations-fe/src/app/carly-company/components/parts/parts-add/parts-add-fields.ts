import {FormGroupHelper} from "../../../../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const engineFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Engine name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'horsePower',
    label: 'Horse power',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'weight',
    label: 'Weight',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'capacity',
    label: 'Capacity',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'numberOfCylinders',
    label: 'Number of cylinders',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  }
];

export const wheelFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  }

];
