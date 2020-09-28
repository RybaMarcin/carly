import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {ValueLabel} from "../../../model/value-label";
import {Tires} from "../../../model/tires.model";

export const tiresDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 4
  },
  {
    inputName: 'preview',
    label: 'Preview',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: []
  },
  {
    inputName: 'type',
    label: 'Type',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 4,
    selectOptions: [...Object.values(Tires.TireType).map(value => ({label: value, value}))]
  },
  {
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 4
  },
];


export const tirePreviews: Array<ValueLabel> = [
  {
    label: 'Preview 1',
    value: 'breaks_1.png'
  },
  {
    label: 'Preview 2',
    value: 'breaks_2.png'
  },
  {
    label: 'Preview 3',
    value: 'breaks_3.png'
  }
];
