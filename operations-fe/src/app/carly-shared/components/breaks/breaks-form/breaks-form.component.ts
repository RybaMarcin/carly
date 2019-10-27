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
import {ValueLabel} from "../../../model/value-label";

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
  @Input() details = false;


  breaksDetailsForm: FormGroup;
  breaksDetailsFormControls = this.fgService.addControlToModel(breaksDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = breaksPreviews;
      }
      return controlModel;
    });

  breaksPreviews: Array<ValueLabel>;

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

    this.breaksPreviews = breaksPreviews;

  }


  onSubmit($event) {

    this.breaksDetailsForm = $event;

    const breaks: Breaks.Model = {
      ...this.breaksDetailsForm.value
    };

    this.createOrUpdateBreaks(breaks);
  }

  createOrUpdateBreaks(breaks: Breaks.Model) {
    let partAction;

    if(this.formAction !== PartFormAction.EDIT) {


      partAction = this.breaksManagementService.createBreaks(breaks);

    } else {
      partAction = this.breaksManagementService.updateBreaks(this.supplyBreaksInfo(breaks));
    }

    partAction.subscribe(data => {
      this.messageService.showMessage('Breaks created!');
      this.submitEvent.emit(breaks);
      this.router.navigate(['/parts/breaks', 'details', data.id, 'edit']);
    },
      error => console.log(error));

  }

  supplyBreaksInfo(breaks: Breaks.Model): Breaks.Model {
    return {
      ...breaks
    }
  }


}
