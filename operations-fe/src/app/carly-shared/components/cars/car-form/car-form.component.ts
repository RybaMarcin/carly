import {Component, EventEmitter, Input, OnInit, ViewChild} from '@angular/core';
import {CarFormAction} from "../../../model/car-form-action.enum";
import {Car} from "../../../model/car.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {Router} from "@angular/router";
import {BreakpointService} from "../../../services/breakpoint.service";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {EngineManagementService} from "../../../resources/engine-management.service";
import {WheelsManagementService} from "../../../resources/wheels-management.service";
import {BreaksManagementService} from "../../../resources/breaks-management.service";
import {Engine} from "../../../model/engine.model";
import {Wheels} from "../../../model/wheels.model";
import {Breaks} from "../../../model/breaks.model";
import {Tires} from "../../../model/tires.model";
import {Breakpoints} from "../../../model/breakpoints.model";
import {carDetailsFormFields, carTypes} from "./car-form-fields";
import {CarManagementService} from "../../../resources/car-management.service";
import {Equipment} from "../../../model/equipment.model";
import {WheelsStepComponent} from "../../wheels/wheels-step/wheels-step.component";
import {wheelsDetailsFormFields, wheelsPreviews} from "../../wheels/wheels-form/wheels-form-fields";
import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {PartStepComponent} from "../../parts/part-step/part-step.component";
import {EngineStepComponent} from "../../engine/engine-step/engine-step.component";
import {BreaksStepComponent} from "../../breaks/breaks-step/breaks-step.component";

@Component({
  selector: 'car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss',
    '../../../styles/form-actions.scss']
})
export class CarFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: CarFormAction;
  @Input() car: Car.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<boolean> = new EventEmitter();

  @ViewChild(EngineStepComponent) engineStepComponent: EngineStepComponent;
  @ViewChild(WheelsStepComponent) wheelsStepComponent: WheelsStepComponent;
  @ViewChild(BreaksStepComponent) breaksStepComponent: BreaksStepComponent;

  generalForm: FormGroup;

  carDetailsForm: FormGroup;
  carDetailsFormControls = this.fgService.addControlToModel(carDetailsFormFields)
    .map(controlModel => {
      if(controlModel.inputName === 'carBody') {
        controlModel.selectOptions = carTypes;
      }
      return controlModel;
    });


  gridColumns = 1;


  ngOnInit() {

    this.carDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.carDetailsFormControls)
    );

    this.carDetailsForm.get('carBody').setValue('body_1.png');

    this.generalForm = this.formBuilder.group({
      carDetailsForm: this.carDetailsForm
    });

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
    this.generalForm.valueChanges.subscribe(() => {
      console.log(500, this.generalForm.controls);
      console.log(510, this.findInvalidControls());
    })

  }


  constructor(
      private formBuilder: FormBuilder,
      private messageService: MessageService,
      private router: Router,
      private breakpointService: BreakpointService,
      private fgService: FormGroupHelperService,
      private carService: CarManagementService
  ) {
  }




  setFormValue(car: Car.Model) {
    this.carDetailsFormControls
      .forEach(control => this.carDetailsForm
        .get(control.inputName)
        .setValue(car[control.inputName]));
  }


  onSubmit() {

    if(this.carDetailsForm.invalid) {
      return;
    }

    const car: Car.Model = {
      ...this.carDetailsForm.value
    };

    car.wheels = this.wheelsStepComponent.wheels;

    this.createOrUpdateCar(car);

  }


  createOrUpdateCar(car: Car.Model) {

    let carAction;


    if(this.formAction !== CarFormAction.EDIT) {
      carAction = this.carService.createCar(car);
    } else {
      carAction = this.carService.updateCar(this.supplyInfo(car));
    }

  }


  supplyInfo(car: Car.Model): Car.Model {
    return {
      ...car
    }
  }


  goBack() {

    this.router.navigate(['/cars']);

  }

  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.generalForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  getCarBodyPreview(): string {
    return this.carDetailsForm.get('carBody').value;
  }


}
