import {FormGroupHelper} from "../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const engineDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'text',
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
    inputName: 'capacity',
    label: 'Capacity',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'numberOfCylinders',
    label: 'Number of cylinders',
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
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  },
];

export const wheelsDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1
  },
  {
    inputName: 'diameter',
    label: 'Diameter',
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
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1
  },
];

export const breaksDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: '',
    rows: 1,
    cols: 1
  },
];

export const tiresDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: '',
    rows: 1,
    cols: 1
  },
];

export const windowsDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: '',
    rows: 1,
    cols: 1
  },
];
