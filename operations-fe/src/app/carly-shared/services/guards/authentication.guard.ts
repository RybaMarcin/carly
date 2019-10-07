import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import {UserManagementService} from "../../resources/user-management.service";
import {LocalStorageService} from "angular-2-local-storage";
import {CARLY_JWT_TOKEN} from "../../model/carly-jwt-token.model";

@Injectable()
export class AuthenticationGuard implements CanActivate, CanActivateChild {

  constructor(
    private router: Router,
    private storageService: LocalStorageService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const jwtToken = this.storageService.get(CARLY_JWT_TOKEN);

    if (jwtToken) {
      return true;
    } else {
      this.router.navigate(['/logout']);
      return false;
    }
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(childRoute, state);
  }

}
