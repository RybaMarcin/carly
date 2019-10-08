import {Component, OnInit, ViewChild} from '@angular/core';
import {DataType} from "../../model/data-type.enum";
import {MatPaginator, MatSort} from '@angular/material';
import {FilterMask} from "../../model/filter.model";
import {MessageService} from "../../services/message.service";
import {ActivatedRoute} from "@angular/router";
import {UserManagementService} from "../../resources/user-management.service";
import {CompanyContextService} from "../../services/company-context.service";
import {CustomerManagementService} from "../../resources/customer-management.service";
import {zip} from "rxjs";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {CustomersDatasource} from "./customers-datasource";

@Component({
  selector: 'app-customers-dashboard',
  templateUrl: './customers-dashboard.component.html',
  styleUrls: ['./customers-dashboard.component.scss']
})
export class CustomersDashboardComponent implements OnInit {

  datasource: CustomersDatasource;
  dataType: DataType;
  id: string;
  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: Array<string> = [
    'forename',
    'surname',
    'email',
    'address'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: 'Code',
      value: 'code'
    },
    {
      label: 'Title',
      value: 'title'
    },
    {
      label: 'Forename',
      value: 'forename'
    },
    {
      label: 'Surname',
      value: 'surname'
    },
    {
      label: 'Email',
      value: 'email'
    },
    {
      label: 'Phone number',
      value: 'phone'
    },
    {
      label: 'Address first line',
      value: 'addressFirstLines'
    },
    {
      label: 'Address second line',
      value: 'addressSecondLines'
    },
    {
      label: 'City',
      value: 'addressCities'
    },
    {
      label: 'Country',
      value: 'addressCountries'
    },
    {
      label: 'Zip Code',
      value: 'zipCode'
    }
  ];

  constructor(
    private messageService: MessageService,
    private customerService: CustomerManagementService,
    private activatedRoute: ActivatedRoute,
    private userService: UserManagementService,
    private companyContext: CompanyContextService
  ) {
  }

  ngOnInit() {

    zip(
      this.activatedRoute.parent.params,
      this.activatedRoute.data
    )
      .subscribe(([params, data]) => {
        this.id = params.id;
        this.dataType = data.dataType;
        this.datasource = new CustomersDatasource(
          this.customerService,
          this.paginator,
          this.messageService,
          this.dataType,
          this.id,
          this.companyContext,
          this.userService
        );
      });

    this.sort.sortChange.subscribe(() => {
      this.loadCustomers();
    });
  }

  loadCustomers() {
    this.datasource.reload(this.buildSearchParams());
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    return params;
  }

}
