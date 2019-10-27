import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {TiresManagementService} from "../../../resources/tires-management.service";
import {Tires} from "../../../model/tires.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {tirePreviews, tiresDetailsFormFields} from "./tires-form-fields";
import {Router} from "@angular/router";
import {ValueLabel} from "../../../model/value-label";

@Component({
  selector: 'tires-form',
  templateUrl: './tires-form.component.html',
  styleUrls: ['./tires-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class TiresFormComponent implements OnInit {

  @Input() tires: Tires.Model;
  @Input() formAction: PartFormAction;
  @Input() submitEvent: EventEmitter<Tires.Model> = new EventEmitter();
  @Input() details = false;

  tiresDetailsForm: FormGroup;
  tiresDetailsFormControls = this.fgService.addControlToModel(tiresDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = tirePreviews;
      }
      return controlModel;
    });

  tirePreviews: Array<ValueLabel>;

  constructor(
    private messageService: MessageService,
    private tiresService: TiresManagementService,
    private fgService: FormGroupHelperService,
    private router: Router
  ) {
  }

  ngOnInit() {

    this.tirePreviews = tirePreviews;

    if(this.tires) {
      console.log(600, this.tires);
    }

  }


  onSubmit($event) {
    this.tiresDetailsForm = $event;

    const tires: Tires.Model = {
      ...this.tiresDetailsForm.value
    };

    this.createOrUpdateTires(tires);

  }

  createOrUpdateTires(tires: Tires.Model) {
    let partAction;

    if(this.formAction !== PartFormAction.EDIT) {


      partAction = this.tiresService.createTires(tires);
    } else {
      partAction = this.tiresService.updateTires(this.supplyWheelsInfo(tires));
    }

    partAction.subscribe(data => {
        this.messageService.showMessage('Tires created!');
        this.submitEvent.emit(tires);
        this.router.navigate(['/parts/tires', 'details', data.id, 'edit']);
      },
      error => console.log(error));

  }

  supplyWheelsInfo(tires: Tires.Model): Tires.Model {
    return {
      ...tires
    }
  }

}
