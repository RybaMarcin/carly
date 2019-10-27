import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {EngineManagementService} from "../../../resources/engine-management.service";
import {MessageService} from "../../../services/message.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {engineDetailsFormFields, enginePreviews} from "./engine-form-fields";
import {Engine} from "../../../model/engine.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {Router} from "@angular/router";
import {UserManagementService} from "../../../resources/user-management.service";
import {BreakpointService} from "../../../services/breakpoint.service";
import {Breakpoints} from "../../../model/breakpoints.model";
import * as moment from 'moment';
import {ValueLabel} from "../../../model/value-label";


@Component({
  selector: 'engine-form',
  templateUrl: './engine-form.component.html',
  styleUrls: ['./engine-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class EngineFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: PartFormAction;
  @Input() engine: Engine.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<boolean> = new EventEmitter();
  @Input() details = false;

  engineDetailsForm: FormGroup;
  engineDetailsFormControls = this.fgService.addControlToModel(engineDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'preview') {
        controlModel.selectOptions = enginePreviews;
      }
      return controlModel;
    });

  enginePreviews: Array<ValueLabel>;

  constructor(
    private engineManagementService: EngineManagementService,
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private router: Router,
  ) {
  }

  ngOnInit() {

    this.enginePreviews = enginePreviews;

  }


  onSubmit($event) {

    this.engineDetailsForm = $event;

    const engine: Engine.Model = {
      ...this.engineDetailsForm.value
    };

    this.createOrUpdateEngine(engine);

  }


  createOrUpdateEngine(engine: Engine.Model) {
    let engineAction;

    if(this.formAction !== PartFormAction.EDIT) {

      engine.createdDate = moment(engine.createdDate, moment.HTML5_FMT.DATETIME_LOCAL).utc(true).format();

      engineAction = this.engineManagementService.createEngine(engine);
    } else {
      engineAction = this.engineManagementService.updateEngine(this.supplyInfo(engine));
    }

    engineAction.subscribe(data => {
      this.messageService.showMessage('Engine created!');
      this.submitEvent.emit(true);
      this.router.navigate(['/parts/engines', 'detail', data.id, 'edit']);
    },
      error => console.log(error));

  }

  supplyInfo(engine: Engine.Model): Engine.Model {
    return {
      ...engine,
      id: engine.id
    }
  }


}
