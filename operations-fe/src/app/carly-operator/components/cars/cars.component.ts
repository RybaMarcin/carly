import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from "@angular/material";
import {FilterBarComponent} from "../../../carly-shared/components/filter-bar/filter-bar.component";
import {FilterMask} from "../../../carly-shared/model/filter.model";
import {MessageService} from "../../../carly-shared/services/message.service";
import {CarManagementService} from "../../../carly-shared/resources/car-management.service";
import {HttpParamsUtils} from "../../../carly-shared/utils/http/http-params-utils";
import {CarsDatasource} from "../../../carly-shared/components/cars/cars-datasource";
import {FormBuilder} from "@angular/forms";
import {FormGroupHelperService} from "../../../carly-shared/services/form-group-helper.service";
import {carsFilterFormFields} from "./cars-filter-fields";

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class CarsComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public carsFilterFormControls = this.fgService.addControlToModel(carsFilterFormFields);
  public carsFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.carsFilterFormControls));

  public generalForm = this.fb.group({
    carsFilterForm: this.carsFilterForm
  });

  public datasource: CarsDatasource;
  public displayedColumns = [
    'name',
    'carCode',
    'createdDate',
    'modifiedDate'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: 'Car name',
      value: 'nameToSearch'
    },
    {
      label: 'Car code',
      value: 'carCode'
    },
    {
      label: 'User',
      value: 'user'
    }
  ];


  constructor(
    private messageService: MessageService,
    private carService: CarManagementService,
    private fb: FormBuilder,
    private fgService: FormGroupHelperService
  ) {
  }

  ngOnInit() {
    this.datasource = new CarsDatasource(this.carService, this.paginator, this.messageService);


    // todo: Uncomment below when backend will be finished.
    // this.sort.sortChange.subscribe(() => {
    //   this.loadCars();
    // });
  }

  clearFilters() {
    this.carsFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  loadCars() {
    this.datasource.reload(this.buildSearchParams());
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.carsFilterFormControls
      .filter(ctrl => !!this.carsFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.carsFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }

}
