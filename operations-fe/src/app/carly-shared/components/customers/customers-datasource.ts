import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {CustomerManagementService} from "../../resources/customer-management.service";
import {MatPaginator} from "@angular/material";
import {MessageService} from "../../services/message.service";
import {DataType} from "../../model/data-type.enum";
import {CompanyContextService} from "../../services/company-context.service";
import {UserManagementService} from "../../resources/user-management.service";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../../model/customer.model";
import {mergeMap} from "rxjs/operators";
import {Roles} from "../../model/roles.model";

export class CustomersDatasource extends AbstractCarlyDataSource<Customer.Model> {

  constructor(
    private customerService: CustomerManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService,
    private dataType: DataType,
    private id: string = null,
    private companyContext: CompanyContextService,
    private userService: UserManagementService
  ) {
    super(messageService);
    this.paginator = this.matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Customer.PaginatedModel> {
    const company = this.companyContext.getCompanyContext();

    return this.userService.getUserContext$()
      .pipe(
        mergeMap(user => {
          const { role } = user;

          if (this.dataType === DataType.GENERAL) {
            if (role === Roles.CARLY_COMPANY) {
              this.id = company.id;
              this.dataType = DataType.COMPANY;
            }
          }

          switch (this.dataType) {
            case DataType.COMPANY:
              return this.customerService.findCustomersByCompanyId(this.id, params);
            default:
              return this.customerService.findCustomers(params);
          }

        })
      );
  }

  connect(): Observable<Customer.Model[]> {
    return this.observePage();
  }

}
