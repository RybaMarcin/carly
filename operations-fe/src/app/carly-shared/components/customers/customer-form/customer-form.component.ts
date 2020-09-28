import {Component, EventEmitter, Input, OnInit} from '@angular/core';
import {Customer} from "../../../model/customer.model";
import {CustomerFormAction} from "../../../model/customer-form-action.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {Router} from "@angular/router";
import {FormGroupHelperService} from "../../../services/form-group-helper.service";
import {BreakpointService} from "../../../services/breakpoint.service";
import {UserManagementService} from "../../../resources/user-management.service";
import {addressDetailsFormFields, customerDetailsFormFields} from "./customer-form-fields";
import {Breakpoints} from "../../../model/breakpoints.model";

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.scss',
    '../../../../carly-shared/styles/side-nav.scss',
    '../../../../carly-shared/styles/buttons.scss']
})
export class CustomerFormComponent implements OnInit {

  @Input() isDisabled = false;
  @Input() formAction: CustomerFormAction;
  @Input() customer: Customer.Model;
  @Input() isRequest = false;
  @Input() submitEvent: EventEmitter<boolean> = new EventEmitter();

  generalForm: FormGroup;

  customerDetailsForm: FormGroup;
  customerDetailsFormControls = this.fgService.addControlToModel(customerDetailsFormFields);

  addressDetailsForm: FormGroup;
  addressDetailsFormControls = this.fgService.addControlToModel(addressDetailsFormFields);

  gridColumns = 4;

  constructor(
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private router: Router,
    private fgService: FormGroupHelperService,
    private breakpointService: BreakpointService,
    private userManagementService: UserManagementService
  ) {
  }

  ngOnInit() {

    this.customerDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.customerDetailsFormControls)
    );

    this.addressDetailsForm = this.formBuilder.group(
      this.fgService.getControlsFromModel(this.addressDetailsFormControls)
    );

    this.generalForm = this.formBuilder.group({
      customerDetailsForm: this.customerDetailsForm,
      addressDetailsForm: this.addressDetailsForm
    });


    if(this.customer) {
      this.setFormValue(this.customer);
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


  setFormValue(customer: Customer.Model) {

    this.customerDetailsFormControls
      .forEach(control => this.customerDetailsForm
        .get(control.inputName)
        .setValue(customer[control.inputName]));


    this.addressDetailsFormControls
      .forEach(control => this.addressDetailsForm
        .get(control.inputName)
        .setValue(customer[control.inputName]));

  }


  onSubmit() {
    if(this.generalForm.invalid) {
      return;
    }
    this.createOrUpdateCustomer();
  }


  createOrUpdateCustomer() {
    let customerAction;

    const customer: Customer.Model = {
      ...this.generalForm.value
    };

    if(this.formAction !== CustomerFormAction.EDIT) {
      customerAction = this.userManagementService.createCustomer(customer);
    } else {
      customerAction = this.userManagementService.updateCustomer(this.supplyCustomerInfo(customer));
    }

    customerAction.subscribe(data => {
      this.messageService.showMessage('Success! Customer data saved!');
      this.submitEvent.emit(true);
      this.router.navigate(['']);
    },
      error => {
        this.messageService.showMessage('Error! Customer data save failed!');
        console.log(error);
      })

  }


  supplyCustomerInfo(customer: Customer.Model): Customer.Model {
    return {
      ...customer
    }
  }


  goBack() {
    this.router.navigate(['']);
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
