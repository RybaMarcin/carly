import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {ValueLabel} from "../../../model/value-label";
import {Tires} from "../../../model/tires.model";

export const windowsDetailsFormFields: FormGroupHelper.Model[] = [
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
    selectOptions: []
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


export const windowsPreviews: Array<ValueLabel> = [
  {
    label: 'Preview 1',
    value: ''
  }
];
