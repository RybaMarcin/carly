import { Injectable } from '@angular/core';
import {LocalStorageService} from "angular-2-local-storage";
import {CustomerManagementService} from "../resources/customer-management.service";
import {Customer, CUSTOMER_CONTEXT} from "../model/customer.model";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerContextService {

  constructor(
    private storageService: LocalStorageService,
    private customerService: CustomerManagementService
  ) {
  }

  getCustomerContext(): Customer.Model {
    return this.storageService.get(CUSTOMER_CONTEXT);
  }



  setCustomerContext(id: string) {

    let customerContext = this.findCustomerById(id);
    console.log("Setting customer context");
    this.storageService.set(CUSTOMER_CONTEXT, customerContext);

  }


  private findCustomerById(customerId: string): Observable<Customer.Model> {

    console.log("Looking for customer by id: " + customerId);

    if(!customerId) {
      return of(null);
    }

    this.customerService.findCustomerById(customerId).subscribe(customer => {
      return of(customer);
    })

  }


}
