import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {MessageService} from "../../services/message.service";
import {FormBuilder} from "@angular/forms";
import {WheelsManagementService} from "../../resources/wheels-management.service";
import {wheelsFilterFormFields} from "./wheels-filter-form";
import {WheelsDatasource} from "./wheels-datasource";
import {FilterMask} from "../../model/filter.model";
import {merge} from "rxjs";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";

@Component({
  selector: 'wheels',
  templateUrl: './wheels.component.html',
  styleUrls: ['./wheels.component.scss', '../../../carly-shared/styles/table-card.scss']
})
export class WheelsComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public wheelsFilterFormControls = this.fgService.addControlToModel(wheelsFilterFormFields);
  public wheelsFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.wheelsFilterFormControls));

  public generalForm = this.fb.group({
    wheelsFilterForm: this.wheelsFilterForm
  });

  public datasource: WheelsDatasource;
  public displayedColumns: Array<string> = [
    'name'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: '',
      value: ''
    }
  ];

  constructor(
    private fgService: FormGroupHelperService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private wheelsService: WheelsManagementService
  ) {
  }

  ngOnInit() {
    this.datasource = new WheelsDatasource(this.wheelsService, this.paginator, this.messageService);

    merge(this.sort.sortChange, this.wheelsFilterForm.value).subscribe(() => this.loadWheels());
  }

  loadWheels() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.wheelsFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.wheelsFilterFormControls
      .filter(ctrl => !!this.wheelsFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.wheelsFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }

}
