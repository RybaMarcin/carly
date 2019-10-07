import {ValueLabel} from "./value-label";
import {FormControl, FormGroup} from "@angular/forms";

export namespace FormGroupHelper {

  export interface ErrorValidators {
    message: string;
    validator: string;
  }

  export interface Model {
    inputName: string;
    label: string;
    validators: Array<any>;
    type: string;
    rows?: number;
    cols?: number;
    group?: boolean;
    inputs?: Array<Model>;
    selectOptions?: Array<ValueLabel>;
    selectMultiple?: boolean;
    isDisabled?: boolean;
    isReadonly?: boolean;
    errors?: ErrorValidators[];
  }

  export interface ModelControl extends Model {
    control: FormControl | FormGroup
  }

  export interface Controls {
    [name: string]: FormControl
  }


}
