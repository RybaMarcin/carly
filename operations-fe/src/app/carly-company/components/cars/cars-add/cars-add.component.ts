import {Component, Input, OnInit} from '@angular/core';
import {CarFormAction} from "../../../../carly-shared/model/car-form-action.enum";

@Component({
  selector: 'app-cars-add',
  templateUrl: './cars-add.component.html',
  styleUrls: ['./cars-add.component.scss']
})
export class CarsAddComponent {

  @Input() disable: boolean;

  public formAction = CarFormAction.CREATE;

  constructor() { }

}
