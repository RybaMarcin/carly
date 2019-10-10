import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Breaks} from "../../model/breaks.model";
import {MessageService} from "../../services/message.service";
import {BreaksManagementService} from "../../resources/breaks-management.service";
import {MatPaginator} from "@angular/material/paginator";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {HttpParams} from "@angular/common/http";

export class BreaksDatasource extends AbstractCarlyDataSource<Breaks.Model> {


  constructor(
    private breaksService: BreaksManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Breaks.Model>> {
    return this.breaksService.findBreaks(params);
  }

  connect(): Observable<Breaks.Model[]> {
    return this.observePage();
  }
}
