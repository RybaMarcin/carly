import {AbstractCarlyDataSource} from "../../utils/table/abstract-datasource";
import {Equipment} from "../../model/equipment.model";
import {MessageService} from "../../services/message.service";
import {MatPaginator} from "@angular/material/paginator";
import {HttpParams} from "@angular/common/http";
import {Page} from "../../model/paginated.model";
import {Observable} from "rxjs";
import {EquipmentManagementService} from "../../resources/equipment-management.service";

export class EquipmentDatasource extends AbstractCarlyDataSource<Equipment.Model>{


  constructor(
    private equipmentService: EquipmentManagementService,
    private matPaginator: MatPaginator,
    messageService: MessageService
  ) {
    super(messageService);
    this.paginator = matPaginator;
  }

  protected loadPage(params: HttpParams): Observable<Page<Equipment.Model>> {
    return this.equipmentService.findEquipment(params);
  }

  connect(): Observable<Equipment.Model[]> {
    return this.observePage();
  }

}
