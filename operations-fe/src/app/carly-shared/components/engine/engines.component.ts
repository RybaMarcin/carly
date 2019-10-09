import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {MessageService} from "../../services/message.service";
import {FormBuilder} from "@angular/forms";
import {EngineManagementService} from "../../resources/engine-management.service";
import {EnginesDatasource} from "./engines-datasource";
import {merge} from "rxjs";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {FilterMask} from "../../model/filter.model";
import {partsFilterFormFields} from "../parts/parts-filter-form";

@Component({
  selector: 'engines',
  templateUrl: './engines.component.html',
  styleUrls: ['./engines.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class EnginesComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public engineFilterFormControls = this.fgService.addControlToModel(partsFilterFormFields);
  public engineFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.engineFilterFormControls));

  public generalForm = this.fb.group({engineFilterForm: this.engineFilterForm});

  public datasource: EnginesDatasource;

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
    private engineService: EngineManagementService
  ) {
  }

  ngOnInit() {
    this.datasource = new EnginesDatasource(this.engineService, this.paginator, this.messageService);

    // todo: Uncomment below when backend will be finished.
    // merge(this.sort.sortChange, this.engineFilterForm.value).subscribe(() => this.loadEngines());
  }

  loadEngines() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.engineFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.engineFilterFormControls
      .filter(control => !!this.engineFilterForm.get(control.inputName).value)
      .forEach(control => {
        params = HttpParamsUtils.appendDateTimeParam(params, control.inputName, this.engineFilterForm.get(control.inputName).value);
      });
    return params;
  }

}
