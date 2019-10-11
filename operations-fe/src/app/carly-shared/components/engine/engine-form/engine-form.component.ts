import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {EngineManagementService} from "../../../resources/engine-management.service";
import {MessageService} from "../../../services/message.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {engineDetailsFormFields} from "./engine-form-fields";
import {Engine} from "../../../model/engine.model";
import {PartFormAction} from "../../../model/part-form-action.model";
import {Router} from "@angular/router";
import {UserManagementService} from "../../../resources/user-management.service";
import {BreakpointService} from "../../../services/breakpoint.service";
import {Breakpoints} from "../../../model/breakpoints.model";
import * as moment from 'moment';


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

  generalForm: FormGroup;

  engineDetailsForm: FormGroup;
  engineDetailsFormControls = this.fgService.addControlToModel(engineDetailsFormFields);

  gridColumns = 1;

  constructor(
    private engineManagementService: EngineManagementService,
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private router: Router,
    private userService: UserManagementService,
    private breakpointService: BreakpointService
  ) {
  }

  ngOnInit() {

    this.engineDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.engineDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      engineDetailsForm: this.engineDetailsForm
    });

    this.engineDetailsForm.get('preview').setValue('engine_1.png');


    if(this.engine) {
      this.setFormValue(this.engine)
    }


    if(this.isDisabled) {
      this.engineDetailsForm.disable();
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
    this.engineDetailsForm.valueChanges.subscribe(() => {
      console.log(500, this.engineDetailsForm.controls);
      console.log(510, this.findInvalidControls());
    })


  }


  setFormValue(engine: Engine.Model) {

    this.engineDetailsFormControls
      .forEach(control => this.engineDetailsForm
        .get(control.inputName)
        .setValue(engine[control.inputName]));

  }


  onSubmit() {
    if(this.generalForm.invalid) {
      return;
    }
    this.createOrUpdateEngine();

  }


  createOrUpdateEngine() {
    let engineAction;

    const engine: Engine.Model = {
      ...this.engineDetailsForm.value
    };

    if(this.formAction !== PartFormAction.EDIT) {

      engine.createDate = moment(engine.createDate, moment.HTML5_FMT.DATETIME_LOCAL).utc(true).format();

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



  goBack() {

    // this.userService.isUserHasRole$(Roles.CARLY_OPERATOR)
    //   .subscribe(hasRole => {
    //     if(hasRole) {
    //       this.router.navigate(['/parts/engines'])
    //     }
    //     this.router.navigate(['/'])
    //   });
    //
    this.router.navigate(['/parts/engines']);
  }


  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.engineDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  getEnginePreview(): string {
    return this.engineDetailsForm.get('preview').value;
  }


}
