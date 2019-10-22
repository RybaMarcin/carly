import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {CompanyFormAction} from "../../../model/company-form-action.model";
import {Company} from "../../../model/company.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {Router} from "@angular/router";
import {BreakpointService} from "../../../services/breakpoint.service";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {addressDetailsFormFields, companyDetailsFormFields} from "./company-form-fields";
import {Breakpoints} from "../../../model/breakpoints.model";
import {UserManagementService} from "../../../resources/user-management.service";
import * as moment from 'moment';
import {CompanyManagementService} from "../../../resources/company-management.service";

@Component({
  selector: 'company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.scss']
})
export class CompanyFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: CompanyFormAction;
  @Input() company: Company.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<boolean> = new EventEmitter();


  generalForm: FormGroup;

  //Company
  companyDetailsForm: FormGroup;
  companyDetailsFormControls = this.fgService.addControlToModel(companyDetailsFormFields);

  //Address
  addressDetailsFormFields: FormGroup;
  addressDetailsFormControls = this.fgService.addControlToModel(addressDetailsFormFields);

  gridColumns = 4;


  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private router: Router,
    private breakpointService: BreakpointService,
    private fgService: FormGroupHelperService,
    private userService: UserManagementService,
    private companyService: CompanyManagementService
  ) { }

  ngOnInit() {

    this.companyDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.companyDetailsFormControls)
    );

    this.addressDetailsFormFields = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.addressDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      companyDetailsForm: this.companyDetailsForm,
      addressDetailsFormFields: this.addressDetailsFormFields
    });

    if(this.company) {
      this.setFormValue(this.company);
    }

    if(this.isDisabled) {
      this.generalForm.disable();
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
    this.generalForm.valueChanges.subscribe(() => {
      console.log(500, this.generalForm.controls);
      console.log(510, this.findInvalidControls());
    })


  }

  setFormValue(company: Company.Model) {

    this.companyDetailsFormControls
      .forEach(control => this.companyDetailsForm
        .get(control.inputName)
        .setValue(company[control.inputName]));


    this.addressDetailsFormControls
      .forEach(control => this.addressDetailsFormFields
        .get(control.inputName)
        .setValue(company[control.inputName]));

  }


  onSubmit() {
    if(this.generalForm.invalid) {
      return;
    }
    //todo: finish implementation with file
    this.createOrUpdateCompany();
  }


  createOrUpdateCompany() {

    let companyAction;

    const company: Company.Model = {
      ...this.companyDetailsForm.value,
    };

    company.address = {
      ...this.addressDetailsFormFields.value
    };


    if(this.formAction !== CompanyFormAction.EDIT) {
      company.createdDate = moment(company.createdDate, moment.HTML5_FMT.DATETIME_LOCAL).utc(true).format();

      companyAction = this.companyService.createCompany(company);
    } else {
      companyAction = this.companyService.updateCompany(this.supplyCompanyInfo(company));
    }

    companyAction.subscribe(data => {
      this.messageService.showMessage('Company saved!');
      this.submitEvent.emit(true);
      this.router.navigate(['/companies', 'detail', data.id, 'edit']);
    },
      error => console.log(error));

  }

  supplyCompanyInfo(company: Company.Model): Company.Model {

    return {
      ...company,
      id: company.id
    }

  }


  goBack() {

    // todo: Uncomment when authorization will be ready.

    // this.userService.isUserHasRole$(Roles.CARLY_OPERATOR)
    //   .subscribe(hasRole => {
    //     if(hasRole) {
    //       this.router.navigate(['/companies']);
    //     }
    //     this.router.navigate(['/']);
    //   })
    this.router.navigate(['/companies']);
  }


  //Method to debug form controls.
  public findInvalidControls() {
    const invalid = [];
    const controls = this.generalForm.controls;
    for(const name in controls) {
      if(controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

}
