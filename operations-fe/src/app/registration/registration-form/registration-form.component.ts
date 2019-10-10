import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../../carly-shared/services/message.service";
import {FormGroupHelperService} from "../../carly-shared/services/form-group-helper.service";
import {registrationDetailsFormFields} from "./registration-form-fields";
import {Router} from "@angular/router";
import {BreakpointService} from "../../carly-shared/services/breakpoint.service";
import {Breakpoints} from "../../carly-shared/model/breakpoints.model";
import {Registration} from "../../carly-shared/model/registration.model";
import {RegistrationService} from "../../carly-shared/services/registration.service";

@Component({
  selector: 'registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss']
})
export class RegistrationFormComponent implements OnInit {


  generalForm: FormGroup;

  registrationDetailsForm: FormGroup;
  registrationDetailsFormControls = this.fgService.addControlToModel(registrationDetailsFormFields);

  gridColumns = 4;


  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private router: Router,
    private breakpointService: BreakpointService,
    private registrationService: RegistrationService
  ) { }

  ngOnInit() {

    this.registrationDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.registrationDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      registrationDetailsForm: this.registrationDetailsForm
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

    setGridColumn(this.breakpointService.getBreakpoint(window.innerWidth));
    this.breakpointService.size.subscribe(setGridColumn);

    //Method below only for debug form controls
    this.registrationDetailsForm.valueChanges.subscribe(() => {
      console.log(500, this.registrationDetailsForm.controls);
      console.log(510, this.findInvalidControls());
    })


  }

  onSubmit() {
    if(this.registrationDetailsForm.invalid) {
      return;
    }

    const user: Registration.Model = {
      ...this.registrationDetailsForm.value
    };

    this.createAccount(user);

  }


  createAccount(user: Registration.Model) {

    this.registrationService.createNewAccount(user)
      .subscribe(() => {
        this.messageService.showMessage('Success! Please check your email and verify.');
        this.router.navigate(['']);
      },
        error => {
          this.messageService.showMessage('Account creation has failed! ');
          this.router.navigate(['']);
          console.log(error);
        });

  }


  goBack() {
    this.router.navigate(['/login']);
  }


  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.registrationDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

}
