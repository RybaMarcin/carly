import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {Engine} from "../../../model/engine.model";
import {ValueLabel} from "../../../model/value-label";


export const engineDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'select',
    cols: 4,
    rows: 1,
    selectOptions: []
  },
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'preview',
    label: 'Preview',
    validators: [Validators.required],
    type: 'select',
    cols: 4,
    rows: 1,
    selectOptions: []
  },
  {
    inputName: 'horsePower',
    label: 'Horse power',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'weight',
    label: 'Weight',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'capacity',
    label: 'Capacity',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1
  },
  {
    inputName: 'numberOfCylinders',
    label: 'Number of cylinders',
    validators: [Validators.required],
    type: 'select',
    cols: 4,
    rows: 1,
    selectOptions: [...Object.values(Engine.Cylinders).map(value => ({label: value, value}))]
  },
  {
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1,
  },
];


export const enginePreviews: Array<ValueLabel> = [
  {
    value: 'engine_1.png',
    label: 'Preview 1'
  },
  {
    value: 'engine_2.png',
    label: 'Preview 2'
  },
  {
    value: 'engine_3.png',
    label: 'Preview 3'
  }
];
