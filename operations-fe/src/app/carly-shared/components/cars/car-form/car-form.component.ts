import {Component, EventEmitter, Input, OnInit} from '@angular/core';
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
import {carDetailsFormFields} from "./car-form-fields";
import {CarManagementService} from "../../../resources/car-management.service";

@Component({
  selector: 'car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.scss', '../../../styles/form-actions.scss']
})
export class CarFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: CarFormAction;
  @Input() car: Car.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<boolean> = new EventEmitter();

  generalForm: FormGroup;

  carDetailsForm: FormGroup;
  carDetailsFormControls = this.fgService.addControlToModel(carDetailsFormFields);


  gridColumns = 1;

  //Parts
  engine: Engine.Model;
  allEngines: Engine.Model[];

  wheels: Wheels.Model;
  allWheels: Wheels.Model[];

  breaks: Breaks.Model;
  allBreaks: Breaks.Model[];

  tires: Tires.Model;
  ngOnInit() {

    this.getAllPartsForCar();

    this.carDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.carDetailsFormControls)
    );

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




  allTires: Tires.Model[];

  constructor(
      private formBuilder: FormBuilder,
      private messageService: MessageService,
      private router: Router,
      private breakpointService: BreakpointService,
      private fgService: FormGroupHelperService,
      private engineService: EngineManagementService,
      private wheelsService: WheelsManagementService,
      private breaksService: BreaksManagementService,
      private carService: CarManagementService
  ) {
  }


  getAllPartsForCar() {
    this.wheelsService.getAllWheels().subscribe(data => {
      this.allWheels = data;
    },
      error => console.log(error)
    );

  }



  setFormValue() {

  }


  onSubmit() {

  }


  createOrUpdateCar() {

    let carAction;

    const car: Car.Model = {
      ...this.carDetailsForm.value
    };


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


}
