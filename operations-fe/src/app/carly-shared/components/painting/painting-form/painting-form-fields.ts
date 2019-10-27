import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";
import {ValueLabel} from "../../../model/value-label";

export const paintingDetailsFormFields: FormGroupHelper.Model[] = [
  {
    inputName: '',
    label: '',
    validators: [Validators.required],
    type: '',
    cols: 4,
    rows: 1,
    selectOptions: []
  }
];


export const paintingPreviews: Array<ValueLabel> = [
  {
    label: '',
    value: ''
  }
];
