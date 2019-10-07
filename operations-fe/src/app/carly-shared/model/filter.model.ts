import {PipeTransform} from "@angular/core";

export interface FilterModel {
  id: string;
  label: string;
  name: string;
  value: string;
}

export interface FilterMask {
  label: string;
  value: string;
  inputValue?: string;
}

export interface FilteredColumnModel {
  id: string;
  label: string;
  name: string;
  visible: boolean;
  filterable: boolean;
  filterParamName: string;
  pipe: PipeTransform;
  pipeValue: string;
}
