import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../carly-shared/services/message.service";
import {FormGroupHelperService} from "../carly-shared/services/form-group-helper.service";
import {BreakpointService} from "../carly-shared/services/breakpoint.service";
import {RegistrationService} from "../carly-shared/services/registration.service";
import {resetPasswordFormFields} from "./reset-password-fields";
import {Registration} from "../carly-shared/model/registration.model";
import {Breakpoints} from "../carly-shared/model/breakpoints.model";

@Component({
  selector: 'forget-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  generalForm: FormGroup;

  resetPasswordForm: FormGroup;
  resetPasswordFormControls = this.fgService.addControlToModel(resetPasswordFormFields);

  gridColumns = 4;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private breakpointService: BreakpointService,
    private registrationService: RegistrationService
  ) { }

  ngOnInit() {

    this.resetPasswordForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.resetPasswordFormControls)
    );

    this.generalForm = this.formBuilder.group({
      resetPasswordForm: this.resetPasswordForm
    });

  }


  onSubmit() {
    const newPassword: Registration.Model = {
      ...this.generalForm.value
    };

    this.registrationService.resetPassword(newPassword)
      .subscribe(data => {
        this.messageService.showMessage('Password reset!');
        this.router.navigate(['']);
      },
        error => {
          this.messageService.showMessage('Error! Reset password failed!');
          console.log(error);
        });

  }

  goBack() {
    this.router.navigate(['']);
  }


}
