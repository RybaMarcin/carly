import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {PartFormAction} from "../../../model/part-form-action.model";
import {Wheels} from "../../../model/wheels.model";
import {MessageService} from "../../../services/message.service";
import {FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {Router} from "@angular/router";
import {wheelsDetailsFormFields, wheelsPreviews} from "./wheels-form-fields";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {ValueLabel} from "../../../model/value-label";

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
  @Input() edit = false;
  @Input() submitEvent: EventEmitter<Wheels.Model> = new EventEmitter();
  @Input() details = false;

  wheelsDetailsForm: FormGroup;
  wheelsDetailsFormControls = this.fgService.addControlToModel(wheelsDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = wheelsPreviews;
      }
      return controlModel;
    });

  wheelsPreviews: Array<ValueLabel>;

  constructor(
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private router: Router,
    private wheelsManagementService: WheelsManagementService
  ) {
  }

  ngOnInit() {

    this.wheelsPreviews = wheelsPreviews;

  }


  onSubmit($event) {

    this.wheelsDetailsForm = $event;

    const wheels: Wheels.Model = {
      ...this.wheelsDetailsForm.value
    };

    this.createOrUpdateWheels(wheels);

  }


  createOrUpdateWheels(wheels: Wheels.Model) {
    let partAction;

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

  enableEdit() {
    this.wheelsDetailsForm.enable();
  }


}
