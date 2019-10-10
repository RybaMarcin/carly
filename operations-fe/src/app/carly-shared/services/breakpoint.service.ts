import { Injectable } from '@angular/core';
import {fromEvent, Observable} from "rxjs";
import {map} from "rxjs/operators";

const BREAKPOINTS = {
  XXS: 540,
  XS: 768,
  SM: 992,
  MD: 1200
};


@Injectable()
export class BreakpointService {

  private _size$: Observable<string>;

  constructor() {
    this._size$ = fromEvent(window, 'resize')
      .pipe(map((e: any) => this.getBreakpoint(e.target.innerWidth)));
  }

  get size(): Observable<string> {
    return this._size$;
  }

  getBreakpoint(width: number): string {
    if (width < BREAKPOINTS.XXS) {
      return "XXS";
    } else if (width < BREAKPOINTS.XS) {
      return "XS";
    } else if (width < BREAKPOINTS.SM) {
      return "SM";
    } else if (width < BREAKPOINTS.MD) {
      return "MD";
    } else {
      return "LG";
    }
  }

}
