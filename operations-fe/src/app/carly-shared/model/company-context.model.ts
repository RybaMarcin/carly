import {Address} from "./address.model";

export const COMPANY_CONTEXT = 'companyContext';

export namespace CompanyContext {
  export interface Model {
    id: string;
    name: string;
    number: string;
    email: string;
    phoneNumber: string;
    yearOfEstablishment: number;
    address: Address;
  }
}
