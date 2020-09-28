import {Route, UrlSegment, UrlSegmentGroup} from "@angular/router";
import {Roles} from "../model/roles.model";

export class CarlyMatcher {

  static carlyOperatorMatcher(
    segments: UrlSegment[],
    group: UrlSegmentGroup,
    route: Route
  ) {
    const user = JSON.parse(window.sessionStorage.getItem('carly-app.userContext'));

    const isUserTypeMatch = user? user.role === Roles.CARLY_OPERATIONS : false;
    if(isUserTypeMatch) {
      return {consumed: []};
    } else {
      return null;
    }
  }

  static carlyCompanyMatcher(
    segments: UrlSegment[],
    group: UrlSegmentGroup,
    route: Route
  ) {
    const user = JSON.parse(window.sessionStorage.getItem('carly-app.userContext'));

    const isUserTypeMatch = user? user.role === Roles.CARLY_COMPANY : false;
    if(isUserTypeMatch) {
      return {consumed: []};
    } else {
      return null;
    }
  }

  static carlyCustomerMatcher(
    segments: UrlSegment[],
    group: UrlSegmentGroup,
    route: Route
  ) {
    const user = JSON.parse(window.sessionStorage.getItem('carly-app.userContext'));

    const isUserTypeMatch = user? user.role === Roles.CARLY_CUSTOMER : false;
    if(isUserTypeMatch) {
      return {consumed: []};
    } else {
      return null;
    }
  }



}
