import {Component, OnInit, ViewChild} from '@angular/core';
import {PartsDashboardDatasource} from "./parts-dashboard-datasource";
import {MatPaginator, MatSort} from '@angular/material';
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {User} from "../../model/user.model";
import {Roles} from "../../model/roles.model";
import {Subject} from "rxjs";
import {HttpParams} from "@angular/common/http";
import {Moment} from 'moment';
import {FilterMask} from "../../model/filter.model";
import {MessageService} from "../../services/message.service";
import {PartManagementService} from "../../resources/part-management.service";
import {ActivatedRoute} from "@angular/router";
import {LocalStorageService} from 'angular-2-local-storage';
import {zip} from "rxjs";
import {debounceTime} from "rxjs/operators";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";


@Component({
  selector: 'app-parts-dashboard',
  templateUrl: './parts-dashboard.component.html',
  styleUrls: ['./parts-dashboard.component.scss']
})
export class PartsDashboardComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  companyId: string;
  CarlyOperator: User['role'] = Roles.CARLY_OPERATOR;
  CarlyCompany: User['role'] = Roles.CARLY_COMPANY;

  minStartDate: Moment;
  maxStartDate: Moment;
  minEndDate: Moment;
  maxEndDate: Moment;
  partTypes: string[] = [];
  minPrice: number;
  maxPrice: number;
  partModels: string[] = [];
  partBrands: string[] = [];
  dateInputs: Subject<HttpParams> = new Subject();

  public datasource: PartsDashboardDatasource;
  public displayedColumns: Array<string> = [
    'name',
    'partCode',
    'partType',
    'brand',
    'model',
    'price'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {label: 'Name', value: 'names'},
    {label: 'Part code', value: 'partCodes'},
    {label: 'Brand name', value: 'brandNames'},
    {label: 'Price', value: 'price'}
  ];

  constructor(
    private messageService: MessageService,
    private partService: PartManagementService,
    private activatedRoute: ActivatedRoute,
    private storageService: LocalStorageService
  ) {
  }

  ngOnInit() {
    zip(this.activatedRoute.parent.params,
      this.activatedRoute.data)
      .subscribe(([params, data]) => {
        const {dataType} = data;
        this.companyId = params.id;
        this.datasource = new PartsDashboardDatasource(this.partService, this.paginator, this.messageService,
          dataType, this.companyId, this.storageService);
      });

    this.dateInputs.pipe(debounceTime(1000)).subscribe(params => {
      this.datasource.reload(params);
    });

    this.sort.sortChange.subscribe(() => {
      this.loadParts();
    })
  }

  clearFilter() {

  }

  loadParts() {
    this.datasource.reload(this.buildSearchParams());
  }

  loadPartsOnChange() {
    this.dateInputs.next(this.buildSearchParams());
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.appendNumberParam(params,'minPrice', this.minPrice);
    params = HttpParamsUtils.appendNumberParam(params, 'maxNumber', this.maxPrice);
    params = HttpParamsUtils.appendDateTimeParam(params, 'minStartDate', this.minStartDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'maxStartDate', this.maxStartDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'minEndDate', this.minEndDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'maxEndDate', this.maxEndDate);
    this.partTypes.forEach((partType) => {
      params = params.append('partTypes', partType);
    });
    this.partModels.forEach((partModel) => {
      params = params.append('partModels', partModel)
    });
    this.partBrands.forEach((partBrand) => {
      params = params.append('partBrands', partBrand)
    });
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    return params;
  }


}
