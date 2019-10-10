import {Component, OnInit, Output, EventEmitter, ViewChild, ElementRef, Input} from '@angular/core';
import {Observable, Subscription, fromEvent} from 'rxjs';
import {ENTER} from '@angular/cdk/keycodes';
import {FormControl, FormArray, FormGroup, FormBuilder} from '@angular/forms';
import {HttpParams} from '@angular/common/http';
import {startWith, map, take, debounceTime, distinctUntilChanged, tap} from 'rxjs/operators';
import {MatAutocomplete, MatChipList, MatAutocompleteSelectedEvent} from '@angular/material';
import {FilterMask, FilterModel} from '../../model/filter.model';

@Component({
  selector: 'filter-bar',
  templateUrl: './filter-bar.component.html',
  styleUrls: ['./filter-bar.component.scss']
})
export class FilterBarComponent implements OnInit {

  @Input() columnsToFilter: FilterMask[] = [];
  @Output() modelUpdateEvent: EventEmitter<HttpParams> = new EventEmitter();

  @ViewChild('filterColumnInput') filterColumnInput: ElementRef;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;
  @ViewChild('chipList') chipList: MatChipList;

  // Filter variables
  public autocompletedFilters$: Observable<FilterMask[]>;
  public separatorKeysCodes: number[] = [ENTER];
  public preventRemove: boolean = false;
  public currentInputSubscription: Subscription;
  public queryParamModel: FilterModel[] = [];
  public filterChanged: boolean = false;

  // Form
  public filterForm: FormGroup;
  public filterValues: FormArray;
  public columnFilterCtrl = new FormControl();

  constructor(
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.filterForm = this.formBuilder.group({
      filterValues: this.formBuilder.array([])
    });

    this.autocompletedFilters$ = this.columnFilterCtrl.valueChanges
      .pipe(
        startWith(''),
        map((filter: FilterMask | string) => filter? this.sieveAutocomplete(filter) : this.columnsToFilter.slice())
      );
  }

  sieveAutocomplete(filterToCompare: FilterMask | string): FilterMask[] {
    if (typeof filterToCompare === "string") {
      return this.columnsToFilter
        .filter(filter => filter.label.toLowerCase().indexOf((filterToCompare as string).toLowerCase()) === 0);
    } else {
      return this.columnsToFilter
        .filter(filter => filter.label.toLowerCase().indexOf((filterToCompare as FilterMask).label.toLowerCase()) === 0);
    }
  }

  onRemoveFilter(filterToRemove: FilterModel) {
    this.queryParamModel = this.queryParamModel
      .filter(filter => filter.id !== filterToRemove.id);
    this.modelUpdateEvent.emit(this.createQueryParamsByModel());
  }

  onSelectOption(event: MatAutocompleteSelectedEvent): void {
    const filter: FilterMask = event.option.value;

    this.filterColumnInput.nativeElement.value = '';
    this.columnFilterCtrl.setValue(null);

    this.appendFilterValueInput();
    this.addQueryParamToModel(filter);
  }

  appendFilterValueInput() {
    this.filterValues = this.filterForm.get('filterValues') as FormArray;
    this.filterValues.push(new FormControl(''));
    this.chipList.chips.changes
      .pipe(take(1))
      .subscribe(chip => chip.last._elementRef.nativeElement.getElementsByTagName('input')[0].focus());
  }

  addQueryParamToModel(filterToAdd: FilterMask) {
    const paramDupes = this.queryParamModel.filter(param => param.name === filterToAdd.value);
    let highestId = 0;

    // I think there is for sure better solution than this...
    paramDupes.forEach(param => {
      const found = param.id.match(/-\d*/)[0];
      const foundId = parseInt(found.substr(1, found.length-1));

      if (foundId > highestId) {
        highestId = foundId;
      }
    });

    this.queryParamModel.push({
      id: `${filterToAdd.value}-${highestId + 1}`,
      label: filterToAdd.label,
      name: filterToAdd.value,
      value: filterToAdd.inputValue || ''
    });
  }

  updateQueryParamModel(filter: FilterModel, value: string) {
    return this.queryParamModel.map(param => {
      let newParam = param;
      if (newParam.id === filter.id) {
        newParam.value = value;
      }
      return newParam;
    });
  }

  onFocus(input: HTMLInputElement, filter: FilterModel) {
    this.preventRemove = true;

    this.currentInputSubscription = fromEvent(input, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap((data: KeyboardEvent) => {
          const value: string = (data.target as HTMLInputElement).value;
          this.updateQueryParamModel(filter, value);
          this.filterChanged = true;
        }),
        debounceTime(850),
        tap((data: KeyboardEvent) => {
          this.modelUpdateEvent.emit(this.createQueryParamsByModel());
          this.filterChanged = false;
        })
      ).subscribe();
  }

  onBlur() {
    this.preventRemove = false;
    if (this.filterChanged) {
      this.modelUpdateEvent.emit(this.createQueryParamsByModel());
    }

    this.filterChanged = false;
    this.currentInputSubscription.unsubscribe();
  }

  createQueryParamsByModel(): HttpParams {
    let params = new HttpParams();
    this.queryParamModel.forEach(({name, value}) => {
      if (value) {
        params = params.append(name, value);
      }
    });
    return params;
  }

  clearFilterBox() {
    this.queryParamModel = [];
    this.modelUpdateEvent.emit(this.createQueryParamsByModel());
  }
}

