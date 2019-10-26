import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {MessageService} from "../../services/message.service";
import {MatPaginator} from "@angular/material/paginator";
import {Observable} from "rxjs";
import {Page} from "../../model/paginated.model";
import {HttpParams} from "@angular/common/http";
import {Windows} from "../../model/windows.model";
import {WindowsManagementService} from "../../resources/windows-management.service";

export class WindowsDatasource extends AbstractCarlyDataSource<Windows.Model> {


  constructor(
    private windowsService: WindowsManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Windows.Model>> {
    return this.windowsService.findWindows(params);
  }

  connect(): Observable<Windows.Model[]> {
    return this.observePage();
  }
}
