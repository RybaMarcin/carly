import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {partsFilterFormFields} from "../parts/parts-filter-form";
import {BreaksDatasource} from "../breaks/breaks-datasource";
import {FilterMask} from "../../model/filter.model";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {MessageService} from "../../services/message.service";
import {FormBuilder} from "@angular/forms";
import {BreaksManagementService} from "../../resources/breaks-management.service";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {WindowsDatasource} from "./windows-datasource";
import {WindowsManagementService} from "../../resources/windows-management.service";

@Component({
  selector: 'windows',
  templateUrl: './windows.component.html',
  styleUrls: ['./windows.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class WindowsComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public windowsFilterFormControls = this.fgService.addControlToModel(partsFilterFormFields);
  public windowsFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.windowsFilterFormControls));

  public generalForm = this.fb.group({
    windowsFilterForm: this.windowsFilterForm
  });

  public datasource: WindowsDatasource;
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
    private windowsService: WindowsManagementService
  ) {
  }

  ngOnInit() {

    this.datasource = new WindowsDatasource(this.windowsService, this.paginator, this.messageService);

  }

  loadBreaks() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.windowsFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.windowsFilterFormControls
      .filter(ctrl => !!this.windowsFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.windowsFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }

}
