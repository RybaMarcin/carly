import {FormGroupHelper} from "../../../../carly-shared/model/form-group-helper.model";
import {Validators} from "@angular/forms";

export const carFormFields: FormGroupHelper.Model[] = [
  {
    inputName: 'name',
    label: 'Car name',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1,
  },
  {
    inputName: 'brand',
    label: 'Brand',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'model',
    label: 'Model',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'accelerate',
    label: 'Accelerate',
    validators: [Validators.required],
    type: 'text',
    rows: 1,
    cols: 1,
  },
  {
    inputName: 'price',
    label: 'Price',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1,
  },
  {
    inputName: 'yearOfProduction',
    label: 'Year of production',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'engine',
    label: 'Engine',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'transmission',
    label: 'Transmission',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'tires',
    label: 'Tires',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'wheels',
    label: 'Wheels',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
  },
  {
    inputName: 'weight',
    label: 'Weight',
    validators: [Validators.required],
    type: 'number',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'numberOfDoors',
    label: 'Number of doors',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'bodyPainting',
    label: 'Color',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
    selectOptions: []
  },
  {
    inputName: 'leasingAvailable',
    label: 'Leasing available',
    validators: [Validators.required],
    type: 'checkbox',
    rows: 1,
    cols: 1,
  },
  {
    inputName: 'equipment',
    label: 'Equipment',
    validators: [Validators.required],
    type: 'select',
    rows: 1,
    cols: 1,
  }
];
