import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatSort} from "@angular/material/sort";
import {partsFilterFormFields} from "../parts/parts-filter-form";
import {WheelsDatasource} from "../wheels/wheels-datasource";
import {FilterMask} from "../../model/filter.model";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {MessageService} from "../../services/message.service";
import {FormBuilder} from "@angular/forms";
import {WheelsManagementService} from "../../resources/wheels-management.service";
import {merge} from "rxjs";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {TiresManagementService} from "../../resources/tires-management.service";
import {TiresDatasource} from "./tires-datasource";

@Component({
  selector: 'tires',
  templateUrl: './tires.component.html',
  styleUrls: ['./tires.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class TiresComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public tiresFilterFormControls = this.fgService.addControlToModel(partsFilterFormFields);
  public tiresFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.tiresFilterFormControls));

  public generalForm = this.fb.group({
    tiresFilterForm: this.tiresFilterForm
  });

  public datasource: TiresDatasource;
  public displayedColumns: Array<string> = [
    'name',
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: 'Name',
      value: 'nameToSearch'
    }
  ];

  constructor(
    private fgService: FormGroupHelperService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private tiresService: TiresManagementService
  ) {
  }

  ngOnInit() {
    this.datasource = new TiresDatasource(this.tiresService, this.paginator, this.messageService);

    // todo: Uncomment below when backend will be finished.
    merge(this.sort.sortChange, this.tiresFilterForm.value).subscribe(() => this.loadWheels());
  }

  loadWheels() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.tiresFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.tiresFilterFormControls
      .filter(ctrl => !!this.tiresFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.tiresFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }
}
