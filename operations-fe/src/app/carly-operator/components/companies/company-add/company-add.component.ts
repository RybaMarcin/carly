import {Component, Input, OnInit} from '@angular/core';
import {CompanyFormAction} from "../../../../carly-shared/model/company-form-action.model";

@Component({
  selector: 'company-add',
  templateUrl: './company-add.component.html',
  styleUrls: ['./company-add.component.scss']
})
export class CompanyAddComponent implements OnInit {

  @Input() disabled: boolean;

  public formAction = CompanyFormAction.CREATE;

  constructor() { }

  ngOnInit() {
  }

}
