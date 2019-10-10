import { Injectable } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {FormGroupHelper} from "../model/form-group-helper.model";

@Injectable()
export class FormGroupHelperService {

  constructor(private formBuilder: FormBuilder) {
  }

  addControlToModel(model: FormGroupHelper.Model[]): FormGroupHelper.ModelControl[] {
    return model.map(input => {
      if(input.group) {
        const controls = input.inputs
          .map(nestedInput => ({...nestedInput, control: new FormControl('', nestedInput.validators)}))
          .reduce((obj, nestedInput) => ({...obj, [nestedInput.inputName]: nestedInput.control}), {});
      return {...input, control: this.formBuilder.group(controls)};
      } else {
        return {...input, control: new FormControl('', input.validators)};
      }
    });
  }

  getControlsFromModel(modelControl: FormGroupHelper.ModelControl[]): FormGroupHelper.Controls {
    return modelControl
      .reduce((obj, item) => ({...obj, [item.inputName]: item.control}), {});
  }

}
