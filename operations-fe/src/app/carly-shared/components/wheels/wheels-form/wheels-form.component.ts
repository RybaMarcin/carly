import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {Wheels} from "../../../model/wheels.model";
import {MessageService} from "../../../services/message.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {Router} from "@angular/router";
import {UserManagementService} from "../../../resources/user-management.service";
import {BreakpointService} from "../../../services/breakpoint.service";
import {wheelsDetailsFormFields, wheelsPreviews} from "./wheels-form-fields";
import {Breakpoints} from "../../../model/breakpoints.model";
import {WheelsManagementService} from "../../../resources/wheels-management.service";

@Component({
  selector: 'wheels-form',
  templateUrl: './wheels-form.component.html',
  styleUrls: ['./wheels-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class WheelsFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() wheels: Wheels.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<Wheels.Model> = new EventEmitter();

  generalForm: FormGroup;

  wheelsDetailsForm: FormGroup;
  wheelsDetailsFormControls = this.fgService.addControlToModel(wheelsDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = wheelsPreviews;
      }
      return controlModel;
    });

  gridColumns = 4;

  constructor(
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private router: Router,
    private userService: UserManagementService,
    private breakpointService: BreakpointService,
    private wheelsManagementService: WheelsManagementService
  ) {
  }

  ngOnInit() {

    this.wheelsDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.wheelsDetailsFormControls)
    );


    this.generalForm = this.formBuilder.group({
        wheelsDetailsForm: this.wheelsDetailsForm
    });


    this.wheelsDetailsForm.get('preview').setValue('wheel_1.png');

    if(this.wheels) {
      this.setFormValue(this.wheels);
    }


    if(this.isDisabled) {
      this.wheelsDetailsForm.disable();
    }


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
    this.wheelsDetailsForm.valueChanges.subscribe(() => {
      console.log(500, this.wheelsDetailsForm.controls);
      console.log(510, this.findInvalidControls());
    })

  }


  setFormValue(wheels: Wheels.Model) {

    this.wheelsDetailsFormControls
      .forEach(control => this.wheelsDetailsForm
        .get(control.inputName)
        .setValue(wheels[control.inputName]));

  }


  ngOnChanges(changes) {
    const { isDisabled, wheel } = changes;
    if(this.wheelsDetailsForm) {
      if(isDisabled) {
        this.updateFormAvalibility(isDisabled.currentValue);
      }
      if(wheel) {
        this.setFormValue(wheel.currentValue);
      }
    }
  }

  updateFormAvalibility(isDisabled: boolean) {
    if(isDisabled) {
      this.wheelsDetailsForm.disable();
    } else {
      this.wheelsDetailsForm.enable();
    }
  }


  onSubmit() {
    if (this.wheelsDetailsForm.invalid) {
      return;
    }
    const wheels = this.wheelsDetailsForm.value;

    this.createOrUpdateWheels();

  }


  createOrUpdateWheels() {
    let partAction;

    const wheels: Wheels.Model = {
      ...this.wheelsDetailsForm.value
    };

    if(this.formAction !== PartFormAction.EDIT) {

      //todo: Implement createdDate here?

      partAction = this.wheelsManagementService.createWheels(wheels);
    } else {
      partAction = this.wheelsManagementService.updateWheels(this.supplyWheelsInfo(wheels));
    }

    partAction.subscribe(data => {
        this.messageService.showMessage('Wheels created!');
        this.submitEvent.emit(wheels);
        this.router.navigate(['/parts/wheels', 'details', data.id, 'edit']);
      },
      error => console.log(error));
  }


  supplyWheelsInfo(wheels: Wheels.Model): Wheels.Model {
    return {
      ...wheels
    }
  }


  goBack() {

    // this.userService.isUserHasRole$(Roles.CARLY_OPERATOR)
    //   .subscribe(hasRole => {
    //     if(hasRole) {
    //       this.router.navigate(['/parts/wheels']);
    //     }
    //     this.router.navigate(['/']);
    //   });

    this.router.navigate(['/parts/wheels'])

  }


  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.wheelsDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  getWheelsPreview(): string {
    return this.wheelsDetailsForm.get('preview').value;
  }


}
