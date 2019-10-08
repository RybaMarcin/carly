import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Engine} from "../../model/engine.model";
import {Wheels} from "../../model/wheels.model";
import {PartManagementService} from "../../resources/part-management.service";
import {MatPaginator} from '@angular/material';
import {MessageService} from "../../services/message.service";
import {DataType} from "../../model/data-type.enum";
import {LocalStorageService} from 'angular-2-local-storage';
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {Roles} from "../../model/roles.model";
import {Car} from "../../model/car.model";
import {Part} from "../../model/part.model";

//todo: Add another models when they will be finished.
export class PartsDashboardDatasource extends AbstractCarlyDataSource<Part.Model>{

  constructor(
    private partService: PartManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService,
    private dataType: DataType,
    private companyId = null,
    private storageService: LocalStorageService
  ) {
    super(messageService);
    this.paginator = this.matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Part.Model>> {
    const company = this.storageService.get('companyContext');
    const user = this.storageService.get('userContext');

    if (this.dataType === DataType.GENERAL) {
      if (user['role'] === Roles.CARLY_COMPANY) {
        this.dataType = DataType.COMPANY;
        this.companyId = company['id'];
      }
    }
    switch (this.dataType) {
      case DataType.COMPANY:
        return this.partService.findPartsByCompanyId(this.companyId, params);
      case DataType.GENERAL:
        return this.partService.findParts(params);
    }
  }

  connect(): Observable<Part.Model[]> {
    return this.observePage();
  }

}
