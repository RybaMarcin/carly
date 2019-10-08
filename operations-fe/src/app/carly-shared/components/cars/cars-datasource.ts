import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Car} from "../../model/car.model";
import {CarManagementService} from "../../resources/car-management.service";
import {MatPaginator} from "@angular/material";
import {MessageService} from "../../services/message.service";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";

export class CarsDatasource extends AbstractCarlyDataSource<Car.Model>{

  constructor(
    private carService: CarManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Car.Model>> {
    return this.carService.findCars(params);
  }

  connect(): Observable<Car.Model[]> {
    return this.observePage();
  }


}
