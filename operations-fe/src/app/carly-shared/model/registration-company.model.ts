import {Address} from "./address.model";

export namespace RegistrationCompany {

  export interface Model {
    name: string;
    brand: string;
    number: string;
    phoneNumber: string;
    email: string;
    password: string;
    matchingPassword: string;
    address: Address;
  }


  export type POST = Model;

}
