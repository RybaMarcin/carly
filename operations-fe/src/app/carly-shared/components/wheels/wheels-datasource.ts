import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Wheels} from "../../model/wheels.model";
import {MessageService} from "../../services/message.service";
import {WheelsManagementService} from "../../resources/wheels-management.service";
import {MatPaginator} from "@angular/material/paginator";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";

export class WheelsDatasource extends AbstractCarlyDataSource<Wheels.Model> {


  constructor(
    private wheelsService: WheelsManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Wheels.Model>> {
    return this.wheelsService.findWheels(params);
  }

  connect(): Observable<Wheels.Model[]> {
    return this.observePage();
  }

}
