import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {ValueLabel} from "../../../model/value-label";

export const paintingDetailsFormFields: FormGroupHelper.Model[] = [
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
  }
];


export const paintingPreviews: Array<ValueLabel> = [
  {
    value: 'painting_1.png',
    label: 'Preview 1'
  },
  {
    value: 'painting_2.png',
    label: 'Preview 2'
  }
];
