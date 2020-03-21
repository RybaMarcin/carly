import {Address} from "./address.model";
import {Page} from "./paginated.model";

export const CUSTOMER_CONTEXT = 'customerContext';

export namespace Customer {

  export interface PreModel {
    id: string;
    code: string;
  }

  export interface Model extends PreModel {
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    cars: Array<string>;
    address: Address;
    gender: Gender;
    createdAt: Date;
  }

  export type PATCH = Model;

  export type POST = Model;

  export type PUT = Model;

  export type PaginatedModel = Page<Model>;

  export enum Gender {
    MALE = 'Male',
    FEMALE = 'Female'
  }

}
