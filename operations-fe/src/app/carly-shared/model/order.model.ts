import {Customer} from "./customer.model";
import {Car} from "./car.model";

export namespace Order {

  export interface Model {
    id: string;
    createdAt: Date;
    status: Status;
    customer: Customer.Model;
    car: Car.Model;
  }

  export enum Status {
    PAID = 'Paid'
  }

  export type PaginatedModel = Model;


}
