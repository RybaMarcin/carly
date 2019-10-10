import { Injectable } from '@angular/core';
import {
  CanActivate,
  CanActivateChild,
  CanLoad,
  Route,
  UrlSegment,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router
} from '@angular/router';
import {Observable, pipe} from 'rxjs';
import {UserManagementService} from "../../resources/user-management.service";
import {CompanyContextService} from "../company-context.service";
import {Roles} from "../../model/roles.model";
import {map} from "rxjs/operators";

@Injectable()
export class CompanyContextGuard implements CanActivate, CanActivateChild {

  constructor(
    private router: Router,
    private userService: UserManagementService,
    private companyContextService: CompanyContextService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.userService.isUserHasRole$(Roles.CARLY_OPERATOR)
      .pipe(
        map(isCarlyOperator => {
          if (isCarlyOperator || this.companyContextService.getCompanyContext()) {
            return true;
          } else {
            this.router.navigate(['/company-context']);
            return false;
          }
        })
      );
  }


  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.canActivate(childRoute, state);
  }
}
