import {Component, OnInit, ViewChild} from '@angular/core';
import {FilterBarComponent} from "../filter-bar/filter-bar.component";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {FormGroupHelperService} from "../../services/form-group-helper.service";
import {partsFilterFormFields} from "../parts/parts-filter-form";
import {FormBuilder} from "@angular/forms";
import {EquipmentDatasource} from "./equipment-datasource";
import {FilterMask} from "../../model/filter.model";
import {MessageService} from "../../services/message.service";
import {EquipmentManagementService} from "../../resources/equipment-management.service";
import {HttpParamsUtils} from "../../utils/http/http-params-utils";

@Component({
  selector: 'equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class EquipmentComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;

  public equipmentFilterFormControls = this.fgService.addControlToModel(partsFilterFormFields);
  public equipmentFilterForm = this.formBuilder.group(this.fgService.getControlsFromModel(this.equipmentFilterFormControls));

  public generalForm = this.formBuilder.group({
    equipmentFilterForm: this.equipmentFilterForm
  });

  public datasource: EquipmentDatasource;
  public displayedColumns: Array<string> = [
    'name'
  ];

  public columnsToFilter: Array<FilterMask> = [
    {
      label: '',
      value: ''
    }
  ];


  constructor(
    private fgService: FormGroupHelperService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private equipmentService: EquipmentManagementService
  ) { }

  ngOnInit() {

    this.datasource = new EquipmentDatasource(
      this.equipmentService, this.paginator, this.messageService
    )

  }


  loadEquipment() {
    this.datasource.reload(this.buildSearchParams());
  }

  clearFilters() {
    this.equipmentFilterForm.reset();
    this.filterBar.clearFilterBox();
  }

  buildSearchParams() {
    let params = this.filterBar.createQueryParamsByModel();
    params = HttpParamsUtils.addSortingParam(params, this.sort);
    this.equipmentFilterFormControls
      .filter(ctrl => !!this.equipmentFilterForm.get(ctrl.inputName).value)
      .forEach(ctrl => {
        params = HttpParamsUtils.appendDateTimeParam(params, ctrl.inputName, this.equipmentFilterForm.get(ctrl.inputName).value);
      });
    return params;
  }


}
