import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {Equipment} from "../../../model/equipment.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {MessageService} from "../../../services/message.service";
import {EquipmentManagementService} from "../../../resources/equipment-management.service";
import {Router} from "@angular/router";
import {equipmentFormFields, equipmentPreviews} from "./equipment-form-fields";
import {ValueLabel} from "../../../model/value-label";

@Component({
  selector: 'equipment-form',
  templateUrl: './equipment-form.component.html',
  styleUrls: ['./equipment-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class EquipmentFormComponent implements OnInit {

  @Input() equipment: Equipment.Model;
  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<Equipment.Model> = new EventEmitter();
  @Input() details = false;


  equipmentDetailsForm: FormGroup;
  equipmentDetailsFormControl = this.fgService.addControlToModel(equipmentFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = equipmentPreviews;
      }
      return controlModel;
    });

  equipmentPreviews: Array<ValueLabel>;

  constructor(
    private fgService: FormGroupHelperService,
    private messageService: MessageService,
    private equipmentService: EquipmentManagementService,
    private router: Router,
  ) {
  }

  ngOnInit() {

    this.equipmentPreviews = equipmentPreviews;

  }


  onSubmit($event) {

    this.equipmentDetailsForm = $event;

    const equipment: Equipment.Model = {
      ...this.equipmentDetailsForm.value
    };

    this.createOrUpdateEquipment(equipment);
  }


  createOrUpdateEquipment(equipment: Equipment.Model) {
    let action;


    if(this.formAction !== PartFormAction.EDIT) {
      action = this.equipmentService.createEquipment(equipment);
    } else {
      action = this.equipmentService.updateEquipment(this.supplyInfo(equipment));
    }

    action.subscribe(data => {
      this.messageService.showMessage('Success! New equipment created!');
      this.router.navigate(['/parts/equipment']);
      this.submitEvent.emit(data);
    },
      error => {
        this.messageService.showMessage('Error! Failed to create equipment!');
        console.log(error);
    })

  }


  supplyInfo(equipment: Equipment.Model): Equipment.Model {
    return {
      ...equipment
    };
  }


}
