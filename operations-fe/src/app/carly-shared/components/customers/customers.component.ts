import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {MessageService} from "../../services/message.service";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {FormBuilder} from "@angular/forms";
import {CustomerManagementService} from "../../resources/customer-management.service";
import {customerFilterFormFields} from "./customer-filter-fields";
import {CustomersDatasource} from "./customers-datasource";
import {FilterMask} from "../../model/filter.model";

@Component({
  selector: 'customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class CustomersComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public customerFilterFormControls = this.fgService.addControlToModel(customerFilterFormFields);
  public customerFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.customerFilterFormControls));

  public generalForm = this.fb.group({
    customerFilterForm: this.customerFilterForm
  });

  public datasource: CustomersDatasource;
  public displayedColumns: Array<string> = [
    'name'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: 'Name',
      value: 'nameToSearch'
    }
  ];

  constructor(
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private fb: FormBuilder,
    private customerService: CustomerManagementService
  ) {
  }

  ngOnInit() {

    // todo: Finish implementation

  }

}
