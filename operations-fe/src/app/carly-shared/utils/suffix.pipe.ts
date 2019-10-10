import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'suffix'
})
export class SuffixPipe implements PipeTransform {

  transform(value: any, suffix?: any): any {
    if (!value) return '';
    if (!suffix) return value;
    return `${value}${suffix}`;
  }

}
