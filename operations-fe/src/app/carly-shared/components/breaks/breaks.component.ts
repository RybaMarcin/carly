import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {FormBuilder} from "@angular/forms";
import {MessageService} from "../../services/message.service";
import {BreaksManagementService} from "../../resources/breaks-management.service";
import {BreaksDatasource} from "./breaks-datasource";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {FilterMask} from "../../model/filter.model";
import {partsFilterFormFields} from "../parts/parts-filter-form";

@Component({
  selector: 'breaks',
  templateUrl: './breaks.component.html',
  styleUrls: ['./breaks.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class BreaksComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public breaksFilterFormControls = this.fgService.addControlToModel(partsFilterFormFields);
  public breaksFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.breaksFilterFormControls));

  public generalForm = this.fb.group({
    breaksFilterForm: this.breaksFilterForm
  });

  public datasource: BreaksDatasource;
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
    private breaksService: BreaksManagementService
  ) {
  }

  ngOnInit() {

    this.datasource = new BreaksDatasource(this.breaksService, this.paginator, this.messageService);

  }

  loadBreaks() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.breaksFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.breaksFilterFormControls
      .filter(ctrl => !!this.breaksFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.breaksFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }

}
