import {CompanyManagementService} from "../../../carly-shared/resources/company-management.service";
import {MessageService} from "../../../carly-shared/services/message.service";
import {MatPaginator} from '@angular/material';
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../../carly-shared/model/paginated.model";
import {Company} from "../../../carly-shared/model/company.model";
import {AbstractCarlyDataSource} from "../../../carly-shared/utils/table/abstract-datasource";

export class CompaniesDatasource extends AbstractCarlyDataSource<Company.Model>{
  constructor(
    private companyService: CompanyManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Company.Model>> {
    return this.companyService.findCompanies(params);
  }

  connect(): Observable<Company.Model[]> {
    return this.observePage();
  }


}
