import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {Breaks} from "../../../model/breaks.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {Router} from "@angular/router";
import {BreakpointService} from "../../../services/breakpoint.service";
import {BreaksManagementService} from "../../../resources/breaks-management.service";
import {breaksDetailsFormFields, breaksPreviews} from "./breaks-form-fields";
import {Breakpoints} from "../../../model/breakpoints.model";
import {CompanyManagementService} from "../../../resources/company-management.service";

@Component({
  selector: 'breaks-form',
  templateUrl: './breaks-form.component.html',
  styleUrls: ['./breaks-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class BreaksFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() breaks: Breaks.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<Breaks.Model> = new EventEmitter();

  generalForm: FormGroup;

  breaksDetailsForm: FormGroup;
  breaksDetailsFormControls = this.fgService.addControlToModel(breaksDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = breaksPreviews;
      }
      return controlModel;
    });

  gridColumns = 4;

  constructor(
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private router: Router,
    private breakpointService: BreakpointService,
    private breaksManagementService: BreaksManagementService,
    private companyManagementService: CompanyManagementService
  ) {
  }

  ngOnInit() {

    this.breaksDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.breaksDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      breaksDetailsForm: this.breaksDetailsForm
    });

    this.breaksDetailsForm.get('preview').setValue('breaks_1.png');

    if(this.breaks) {
      this.setFormValue(this.breaks);
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
    this.breaksDetailsForm.valueChanges.subscribe(() => {
      console.log(500, this.breaksDetailsForm.controls);
      console.log(510, this.findInvalidControls());
    })

  }


  setFormValue(breaks: Breaks.Model) {

    this.breaksDetailsFormControls
      .forEach(control => this.breaksDetailsForm
        .get(control.inputName)
        .setValue(breaks[control.inputName]));

  }

  ngOnChanges(changes) {
    const { isDisabled, wheel } = changes;
    if(this.breaksDetailsForm) {
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
      this.breaksDetailsForm.disable();
    } else {
      this.breaksDetailsForm.enable();
    }
  }


  onSubmit() {
    if(this.breaksDetailsForm.invalid) {
      return;
    }

    this.createOrUpdateBreaks();
  }

  createOrUpdateBreaks() {
    let partAction;

    const breaks: Breaks.Model = {
      ...this.breaksDetailsForm.value
    };

    if(this.formAction !== PartFormAction.EDIT) {


      partAction = this.breaksManagementService.createBreaks(breaks);

    } else {
      partAction = this.breaksManagementService.updateBreaks(this.supplyBreaksInfo(breaks));
    }

    partAction.subscribe(data => {
      this.messageService.showMessage('Breaks created!');
      this.submitEvent.emit(true);
      this.router.navigate(['/parts/breaks', 'details', data.id, 'edit']);
    },
      error => console.log(error));

  }

  supplyBreaksInfo(breaks: Breaks.Model): Breaks.Model {
    return {
      ...breaks
    }
  }


  goBack() {

    // todo: finish implementation
    this.router.navigate(['/parts/breaks']);
  }


  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.breaksDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }


  getBreaksPreview(): string {
    return this.breaksDetailsForm.get('preview').value;
  }

}
