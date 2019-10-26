import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {Validators} from "@angular/forms";

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
