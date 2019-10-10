import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Engine} from "../../model/engine.model";
import {EngineManagementService} from "../../resources/engine-management.service";
import {HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {MatPaginator} from "@angular/material/paginator";
import {MessageService} from "../../services/message.service";

export class EnginesDatasource  extends AbstractCarlyDataSource<Engine.Model> {

  constructor(
    private engineService: EngineManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }


  protected loadPage(params: HttpParams): Observable<Page<Engine.Model>> {
    return this.engineService.findEngines(params);
  }

  connect(): Observable<Engine.Model[]> {
    return this.observePage();
  }

}
