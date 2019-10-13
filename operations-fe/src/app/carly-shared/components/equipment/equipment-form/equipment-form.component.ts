import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {Equipment} from "../../../model/equipment.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {MessageService} from "../../../services/message.service";
import {EquipmentManagementService} from "../../../resources/equipment-management.service";
import {Router} from "@angular/router";
import {equipmentFormFields} from "./equipment-form-fields";
import {BreakpointService} from "../../../services/breakpoint.service";

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


  generalForm: FormGroup;

  equipmentDetailsForm: FormGroup;
  equipmentDetailsFormControl = this.fgService.addControlToModel(equipmentFormFields);

  gridColumns = 4;


  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private messageService: MessageService,
    private equipmentService: EquipmentManagementService,
    private router: Router,
    private breakpointService: BreakpointService
  ) {
  }

  ngOnInit() {

    this.equipmentDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.equipmentDetailsFormControl)
    );

    this.generalForm = this.formBuilder.group({
      equipmentDetailsForm: this.equipmentDetailsForm
    });

    if(this.isDisabled) {
      this.generalForm.disable();
    }

    if(this.equipment) {
      this.setFormValue(this.equipment);
    }

  }


  setFormValue(equipment: Equipment.Model) {

    this.equipmentDetailsFormControl
      .forEach(control => this.equipmentDetailsForm
        .get(control.inputName)
        .setValue(equipment[control.inputName]));

  }



  onSubmit() {
    if(this.generalForm.invalid) {
      return;
    }
    this.createOrUpdateEquipment();
  }


  createOrUpdateEquipment() {
    let action;

    const equipment: Equipment.Model = {
      ...this.generalForm.value
    };


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

  goBack() {
    this.router.navigate(['/parts/equipment']);
  }


  public findInvalidControls() {
    const invalid = [];
    const controls = this.equipmentDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid){
        invalid.push(name);
      }
    }
    return invalid;
  }



}
