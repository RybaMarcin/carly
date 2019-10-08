import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const carDetailsFormFields: FormGroupHelper.Model[] = [
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
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'model',
    label: 'Model',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  }
];
