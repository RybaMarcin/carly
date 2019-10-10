import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'forIn'
})
export class ForInPipe implements PipeTransform {

  transform(object: object): Array<object> {
    return object? Object.entries(object).map(([key, value]) => ({key, value})) : null;
  }

}
