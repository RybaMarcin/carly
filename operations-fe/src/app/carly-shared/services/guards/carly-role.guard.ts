import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, of, zip} from 'rxjs';
import {UserManagementService} from "../../resources/user-management.service";
import {map, mergeMap} from "rxjs/operators";

@Injectable()
export class CarlyRoleGuard implements CanActivate, CanActivateChild {

  constructor(
    private userService: UserManagementService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): Observable<boolean> {
    return of(route.data)
      .pipe(
        mergeMap(({roles}) => zip(...roles.map(role => this.userService.isUserHasRole$(role)))),
        map(roles => !!roles.find(roleFound => roleFound === true))
      )
  }

  canActivateChild(route: ActivatedRouteSnapshot): Observable<boolean> {
    return this.canActivate(route);
  }

}
