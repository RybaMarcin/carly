import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {FilterBarComponent} from "../../../carly-shared/components/filter-bar/filter-bar.component";
import {MessageService} from "../../../carly-shared/services/message.service";
import {FormGroupHelperService} from "../../../carly-shared/services/form-group-helper.service";
import {FormBuilder} from "@angular/forms";
import {CompanyManagementService} from "../../../carly-shared/resources/company-management.service";
import {companiesFilterFormFields} from "./companies-filter-fields";
import {CompaniesDatasource} from "./companies-datasource";
import {FilterMask} from "../../../carly-shared/model/filter.model";
import {merge} from "rxjs";
import {HttpParamsUtils} from "../../../carly-shared/utils/http/http-params-utils";

@Component({
  selector: 'companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class CompaniesComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public companyFilterFormControls = this.fgService.addControlToModel(companiesFilterFormFields);
  public companyFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.companyFilterFormControls));

  public generalForm = this.fb.group({companyFilterForm: this.companyFilterForm});

  public datasource: CompaniesDatasource;
  public displayedColumns: Array<string> = [
    'name'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: 'Company name',
      value: 'nameToSearch'
    },
    {
      label: 'Company code',
      value: 'companyCode'
    },
    {
      label: 'User',
      value: 'user'
    }
  ];

  constructor(
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private fb: FormBuilder,
    private companyService: CompanyManagementService
  ) {
  }

  ngOnInit() {
    this.datasource = new CompaniesDatasource(this.companyService, this.paginator, this.messageService);

    // todo: Uncomment below when backend will be finished.
    // merge(this.sort.sortChange, this.companyFilterForm.value).subscribe(() => this.loadCompanies());
  }

  loadCompanies() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.companyFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.companyFilterFormControls
      .filter(ctrl => !!this.companyFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.companyFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }

}
