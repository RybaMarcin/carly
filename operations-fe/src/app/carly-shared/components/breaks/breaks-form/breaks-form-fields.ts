import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {Breaks} from "../../../model/breaks.model";
import {ValueLabel} from "../../../model/value-label";

export const breaksDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Name',
    validators: [Validators.required],
    type: 'text',
    cols: 4,
    rows: 1
  },
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
    inputName: 'preview',
    label: 'Preview',
    validators: [Validators.required],
    type: 'select',
    cols: 4,
    rows: 1,
    selectOptions: []
  },
  {
    inputName: 'type',
    label: 'Type',
    validators: [Validators.required],
    type: '',
    cols: 4,
    rows: 1,
    selectOptions: [...Object.values(Breaks.BreaksType).map(value => ({label: value, value}))]
  },
  {
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    cols: 4,
    rows: 1
  }
];


export const breaksPreviews: Array<ValueLabel> = [
  {
    value: '',
    label: 'Preview 1'
  },
  {
    value: '',
    label: 'Preview 2'
  },
  {
    value: '',
    label: 'Preview 3'
  }
];
