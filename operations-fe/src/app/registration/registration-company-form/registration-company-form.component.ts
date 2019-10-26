import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../carly-shared/services/form-group-helper.service";
import {addressDetailsFormFields, registrationCompanyDetailsFormFields} from "./registration-company-form-fields";
import {MessageService} from "../../carly-shared/services/message.service";
import {Router} from "@angular/router";
import {BreakpointService} from "../../carly-shared/services/breakpoint.service";
import {RegistrationService} from "../../carly-shared/services/registration.service";
import {Breakpoints} from "../../carly-shared/model/breakpoints.model";
import {RegistrationCompany} from "../../carly-shared/model/registration-company.model";

@Component({
  selector: 'registration-company-form',
  templateUrl: './registration-company-form.component.html',
  styleUrls: ['./registration-company-form.component.scss']
})
export class RegistrationCompanyFormComponent implements OnInit {


  generalForm: FormGroup;

  registrationCompanyDetailsForm: FormGroup;
  registrationCompanyDetailsControls = this.fgService.addControlToModel(registrationCompanyDetailsFormFields);

  addressCompanyDetailsForm: FormGroup;
  addressCompanyDetailsControls = this.fgService.addControlToModel(addressDetailsFormFields);

  gridColumns = 4;

  constructor(
    private fgService: FormGroupHelperService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private router: Router,
    private breakpointService: BreakpointService,
    private registrationService: RegistrationService
  ) { }

  ngOnInit() {

    this.registrationCompanyDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.registrationCompanyDetailsControls)
    );

    this.addressCompanyDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.addressCompanyDetailsControls)
    );

    this.generalForm = this.formBuilder.group({
      registrationCompanyDetailsForm: this.registrationCompanyDetailsForm,
      addressCompanyDetailsForm: this.addressCompanyDetailsForm
    });


    const setGridColumn = (breakpoint: string) => {
      switch(breakpoint) {
        case Breakpoints.XXS:
          this.gridColumns = 1;
          break;
        case Breakpoints.XS || Breakpoints.SM:
          this.gridColumns = 2;
          break;
        default:
          this.gridColumns = 4;
      }
    };


  }



  onSubmit() {
    if(this.registrationCompanyDetailsForm.invalid) {
      return;
    }

    const company: RegistrationCompany.Model = {
      ...this.registrationCompanyDetailsForm.value
    };


    this.createCompanyAccount(company);
  }


  createCompanyAccount(company: RegistrationCompany.Model) {

    this.registrationService.createNewCompanyAccount(company)
      .subscribe(() => {
        this.messageService.showMessage('Success! New account created');
        this.router.navigate(['']);
      },
        error => {
          this.messageService.showMessage('Account creation has failed!');
          this.router.navigate(['']);
          console.log(error);
        });
  }


  goBack() {
    this.router.navigate(['']);
  }


}
