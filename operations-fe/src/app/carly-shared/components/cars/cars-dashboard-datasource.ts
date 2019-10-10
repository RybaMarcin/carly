import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Car} from "../../model/car.model";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {CarManagementService} from "../../resources/car-management.service";
import {MessageService} from "../../services/message.service";
import {DataType} from "../../model/data-type.enum";
import {LocalStorageService} from 'angular-2-local-storage';
import {Roles} from "../../model/roles.model";
import {MatPaginator} from '@angular/material';

export class CarsDashboardDatasource extends AbstractCarlyDataSource<Car.Model>{

  constructor(
    private carService: CarManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService,
    private dataType: DataType,
    private companyId,
    private storageService: LocalStorageService
  ) {
    super(messageService);
    this.paginator = this.matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Car.Model>> {
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
        return this.carService.findCarsByCompanyId(this.companyId, params);
      case DataType.GENERAL:
        return this.carService.findCars(params);
    }
  }

  connect(): Observable<Car.Model[]> {
    return this.observePage();
  }

}
