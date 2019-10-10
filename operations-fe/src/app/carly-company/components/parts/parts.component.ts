import {Component, OnInit, ViewChild} from '@angular/core';
import {FilterBarComponent} from "../../../carly-shared/components/filter-bar/filter-bar.component";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MessageService} from "../../../carly-shared/services/message.service";
import {FormGroupHelperService} from "../../../carly-shared/services/form-group-helper.service";
import {FormBuilder} from "@angular/forms";
import {PartManagementService} from "../../../carly-shared/resources/part-management.service";
import {FilterMask} from "../../../carly-shared/model/filter.model";
import {partsFilterFomFields} from "./parts-filter-fields";

@Component({
  selector: 'app-parts',
  templateUrl: './parts.component.html',
  styleUrls: ['./parts.component.scss']
})
export class PartsComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('filterBar') filterBar: FilterBarComponent;
  @ViewChild(MatSort) sort: MatSort;


  public partFilterFormControls = this.fgService.addControlToModel(partsFilterFomFields);
  public partFilterForm = this.fb.group(this.fgService.getControlsFromModel(this.partFilterFormControls));


  //todo: Datasource here!

  public displayedColumns: Array<string> = [
    'name',
    'partCode',
    'createdDate',
    'modifiedDate'
  ];

  // todo: Finish columns
  public columnsToFilter: Array<FilterMask> = [
    {
      label: ' ',
      value: ''
    }
  ];


  constructor(
    private messageService: MessageService,
    private fgService: FormGroupHelperService,
    private fb: FormBuilder,
    private partService: PartManagementService
  ) {
  }


  ngOnInit() {



  }


  loadParts() {

  }


  clearFilters() {
    this.partFilterForm.reset();
    this.filterBar.clearFilterBox();
  }


  buildSearchParams() {

  }

}
