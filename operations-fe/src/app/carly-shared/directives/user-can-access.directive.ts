import {Directive, Input, OnDestroy, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';
import {Roles} from "../model/roles.model";
import {Subscription} from "rxjs";
import {UserManagementService} from "../resources/user-management.service";
import {first, map} from "rxjs/operators";

@Directive({
  selector: '[userCanAccess]'
})
export class UserCanAccessDirective implements OnInit, OnDestroy {

  @Input('userCanAccess') userCanAccess: Roles[];
  private permission$: Subscription;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private userService: UserManagementService
  ) {
  }

  ngOnInit(): void {
    this.applyPermission();
  }

  private applyPermission(): void {
    this.permission$ = this.userService.getUserContext$()
      .pipe(
        first(),
        map(user => {
          if (this.userCanAccess) {
            return !!this.userCanAccess.find(role => role === user.role);
          } else {
            return true;
          }
        })
      )
      .subscribe(isAllowed => {
        if(!isAllowed) {
          this.viewContainer.clear();
        } else {
          this.viewContainer.createEmbeddedView(this.templateRef);
        }
      });
  }

  ngOnDestroy(): void {
    this.permission$.unsubscribe();
  }

}
