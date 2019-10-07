import {HttpParams} from "@angular/common/http";
import {Moment} from 'moment';
import {MatSort} from '@angular/material';
import {DATE_FORMAT_ISO_8601} from '../date-formatter';
import {FormGroup} from "@angular/forms";

export class HttpParamsUtils {
  static appendNumberParam(params: HttpParams, paramName: string, paramValue: number) {
    if (paramValue) {
      return params.append(paramName, paramValue.toString());
    }
    return params;
  }

  static appendDateParam(params: HttpParams, paramName: string, paramValue: Moment): HttpParams {
    if (paramValue) {
      return params.append(paramName, paramValue.utc(true).format(DATE_FORMAT_ISO_8601));
    }
    return params;
  }

  static appendDateTimeParam(params: HttpParams, paramName: string, paramValue: Moment): HttpParams {
    if (paramValue) {
      return params.append(paramName, paramValue.utc(true).toISOString());
    }
    return params;
  }

  static addSortingParam(params: HttpParams, sort: MatSort): HttpParams {
    if (sort.active != null && sort.direction !== '') {
      return params.append('sort', `${sort.active},${sort.direction}`);
    }
    return params;
  }

  static addFormParam(params: HttpParams, formGroup: FormGroup, formControlName: string): HttpParams {
    const value = formGroup.get(formControlName).value;
    if (value != null) {
      return params.append(formControlName, value);
    }
    return params;
  }

  static addFormParamArrayValue(params: HttpParams, formGroup: FormGroup, formControlName: string): HttpParams {
    const value = formGroup.get(formControlName).value;
    if(value != null && value instanceof Array) {
      params = params.append(formControlName, value.join(','));
    }
    return params;
  }

  static addFormRangeParam(params: HttpParams, formGroup: FormGroup, formControlName: string, minParamName: string, maxParamName: string): HttpParams {
    const value = formGroup.get(formControlName).value[0];
    if (value != null) {
      params = params.append(minParamName, value);
    }
    const highValue = formGroup.get(formControlName).value[1];
    if (highValue != null) {
      params = params.append(maxParamName, highValue);
    }
    return params;
  }
}
