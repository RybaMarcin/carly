import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {Windows} from "../../../model/windows.model";
import {FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {Router} from "@angular/router";
import {WindowsManagementService} from "../../../resources/windows-management.service";
import {windowsDetailsFormFields, windowsPreviews} from "./windows-form-fields";
import {ValueLabel} from "../../../model/value-label";
import {Wheels} from "../../../model/wheels.model";

@Component({
  selector: 'windows-form',
  templateUrl: './windows-form.component.html',
  styleUrls: ['./windows-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class WindowsFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() windows: Windows.Model;
  @Input() isRequest = false;
  @Input() edit = false;
  @Input() submitEvent: EventEmitter<Windows.Model> = new EventEmitter();
  @Input() details = false;


  windowsDetailsForm: FormGroup;
  windowsDetailsFormControls = this.fgService.addControlToModel(windowsDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = windowsPreviews;
      }
      return controlModel;
    });

  windowsPreviews: Array<ValueLabel>;

  constructor(
    private messageService: MessageService,
    private windowsService: WindowsManagementService,
    private fgService: FormGroupHelperService,
    private router: Router
  ) {
  }

  ngOnInit() {

    this.windowsPreviews = windowsPreviews;

  }

  onSubmit($event) {
    this.windowsDetailsForm = $event;

    const windows: Windows.Model = {
      ...this.windowsDetailsForm.value
    };

    this.createOrUpdateWindows(windows);

  }

  createOrUpdateWindows(windows: Windows.Model) {
    let partAction;

    if(this.formAction !== PartFormAction.EDIT) {


      partAction = this.windowsService.createWindows(windows);
    } else {
      partAction = this.windowsService.updateWindows(this.supplyWheelsInfo(windows));
    }

    partAction.subscribe(data => {
        this.messageService.showMessage('Tires created!');
        this.submitEvent.emit(windows);
        this.router.navigate(['/parts/tires', 'details', data.id, 'edit']);
      },
      error => console.log(error));

  }

  supplyWheelsInfo(windows: Windows.Model): Windows.Model {
    return {
      ...windows
    }
  }




}
