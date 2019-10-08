import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {User} from "../../model/user.model";
import {Roles} from "../../model/roles.model";
import {Moment} from 'moment';
import {FilterMask} from "../../model/filter.model";
import {MessageService} from "../../services/message.service";
import {CompanyManagementService} from "../../resources/company-management.service";
import {ActivatedRoute} from "@angular/router";
import {LocalStorageService} from 'angular-2-local-storage';
import {Subject, zip} from "rxjs";
import {HttpParams} from "@angular/common/http";
import {debounceTime} from "rxjs/operators";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {CarsDashboardDatasource} from "./cars-dashboard-datasource";
import {CarManagementService} from "../../resources/car-management.service";

@Component({
  selector: 'app-cars-dashboard',
  templateUrl: './cars-dashboard.component.html',
  styleUrls: ['./cars-dashboard.component.scss']
})
export class CarsDashboardComponent implements OnInit {

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
  carTypes: string[] = [];
  minPrice: number;
  maxPrice: number;
  carModels: string[] = [];
  carBrands: string[] = [];
  dateInputs: Subject<HttpParams> = new Subject();


  public datasource: CarsDashboardDatasource;
  public displayedColumns: Array<string> = [
    'name',
    'carCode',
    'carType',
    'brand',
    'model',
    'startDate',
    'endDate'
  ];

  public columnsToFiler: Array<FilterMask> = [
    {label: 'Name', value: 'names'},
    {label: 'Car code', value: 'carCodes'},
    {label: 'Brand name', value: 'brandNames'},
    {label: 'Model', value: 'models'}
  ];



  constructor(
    private messageService: MessageService,
    private carService: CarManagementService,
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
        this.datasource = new CarsDashboardDatasource(this.carService, this.paginator, this.messageService,
          dataType, this.companyId, this.storageService);
      });

    this.dateInputs.pipe(debounceTime(1000)).subscribe(params => {
      this.datasource.reload(params);
    });

    this.sort.sortChange.subscribe(() => {
      this.loadCars();
    })
  }

  clearFilters() {
    this.minPrice = null;
    this.maxPrice = null;
    this.minStartDate = null;
    this.maxStartDate = null;
    this.minEndDate = null;
    this.maxEndDate = null;
    this.carBrands = null;
    this.carModels = null;
    this.carTypes = null;
    this.filterBar.clearFilterBox();
  }

  loadCars() {
    this.datasource.reload(this.buildSearchParams());
  }

  loadCarsOnChange() {
    this.dateInputs.next(this.buildSearchParams());
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.appendNumberParam(params, 'minPrice', this.minPrice);
    params = HttpParamsUtils.appendNumberParam(params, 'maxPrice', this.maxPrice);
    params = HttpParamsUtils.appendDateTimeParam(params, 'minStartDate', this.minStartDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'maxStartDate', this.maxStartDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'minEndDate', this.minEndDate);
    params = HttpParamsUtils.appendDateTimeParam(params, 'maxEndDate', this.maxEndDate);
    this.carTypes.forEach((carType) => {
      params = params.append('carTypes', carType);
    });
    this.carModels.forEach((carModel) => {
      params = params.append('carModels', carModel);
    });
    this.carBrands.forEach((carBrand) => {
      params = params.append('carBrands', carBrand);
    });
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    return params;
  }




}
