import {Pipe, PipeTransform} from '@angular/core';
import * as moment from 'moment';
import {DATE_FORMAT_ISO_8601, DATE_TIME_FORMAT_ISO_8601} from '../utils/date-formatter';

@Pipe({name: 'DateISO8601'})
export class DateISO8601Pipe implements PipeTransform {
  transform(dateString: string): string {
    return moment(dateString).utc(true).format(DATE_FORMAT_ISO_8601);
  }
}

@Pipe({name: 'DateTimeISO8601'})
export class DateTimeISO8601Pipe implements PipeTransform {
  transform(dateString: string): string {
    return moment(dateString).utc(true).format(DATE_TIME_FORMAT_ISO_8601);
  }
}