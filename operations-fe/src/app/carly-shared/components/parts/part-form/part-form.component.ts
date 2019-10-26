import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {FormGroupHelper} from "../../../model/form-group-helper.model";
import {BreakpointService} from "../../../services/breakpoint.service";
import {Breakpoints} from "../../../model/breakpoints.model";
import {Router} from "@angular/router";
import {ValueLabel} from "../../../model/value-label";

@Component({
  selector: 'part-form',
  templateUrl: './part-form.component.html',
  styleUrls: ['./part-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class PartFormComponent implements OnInit {

  @Input() part: any;
  @Input() partFields: FormGroupHelper.Model[];
  @Input() partFormDetailsControls: FormGroupHelper.ModelControl[];
  @Input() partGroup: string;
  @Input() partPreviews: Array<ValueLabel>;
  @Input() isDisabled: boolean;
  @Output() partCurrentForm = new EventEmitter();


  generalForm: FormGroup;

  partDetailsForm: FormGroup;

  gridColumns = 4;

  constructor(
    private formBuilder: FormBuilder,
    private fgService: FormGroupHelperService,
    private breakpointService: BreakpointService,
    private router: Router
  ) {
  }

  ngOnInit() {

    this.partDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.partFormDetailsControls)
    );

    this.generalForm = this.formBuilder.group({
      partDetailsForm: this.partDetailsForm
    });


    this.partDetailsForm.get('preview').setValue(this.partPreviews[0].value);


    if(this.part) {
      this.setFormValue(this.part);
    }


    if(this.isDisabled) {
      this.partDetailsForm.disable();
    }


    const setGridColumn = (breakpoint: string) => {
      switch(breakpoint) {
        case Breakpoints.XXS:
          this.gridColumns = 1;
          break;
        case Breakpoints.XS || Breakpoints.SM:
          this.gridColumns = 2;
          break;
        default:
          this.gridColumns = 4;
      }
    };

    setGridColumn(this.breakpointService.getBreakpoint(window.innerWidth));
    this.breakpointService.size.subscribe(setGridColumn);

    //Method below only for debug form controls
    this.partDetailsForm.valueChanges.subscribe(() => {
      console.log(500, this.partDetailsForm.controls);
      console.log(510, this.findInvalidControls());
    })

  }


  setFormValue(part: any) {

    this.partFormDetailsControls
      .forEach(control => this.partDetailsForm
        .get(control.inputName)
        .setValue(part[control.inputName]));

  }

  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.partDetailsForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  getPartPreview(): string {
    return this.partDetailsForm.get('preview').value;
  }

  onSubmit() {
    if(this.partDetailsForm.invalid) {
      return;
    }
    this.partCurrentForm.emit(this.partDetailsForm);
  }

  goBack() {
    this.router.navigate(['/parts/' + this.partGroup]);
  }

}
