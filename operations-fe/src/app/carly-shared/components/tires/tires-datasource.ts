import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Tires} from "../../model/tires.model";
import {MatPaginator} from "@angular/material/paginator";
import {MessageService} from "../../services/message.service";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {TiresManagementService} from "../../resources/tires-management.service";

export class TiresDatasource extends AbstractCarlyDataSource<Tires.Model> {


  constructor(
    private tiresService: TiresManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Tires.Model>> {
    return this.tiresService.findTires(params);
  }

  connect(): Observable<Tires.Model[]> {
    return this.observePage();
  }


}
