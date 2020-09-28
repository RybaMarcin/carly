import {Address} from "./address.model";
import {Page} from "./paginated.model";
import {ReviewChanges} from "./review-changes.model";

export namespace Company {

  import ChangeRequestStatus = ReviewChanges.ChangeRequestStatus;

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel {
    brand: string;
    number: string;
    email: string;
    phoneNumber: string;
    yearOfEstablishment: number;
    // logoId: number;
    address: Address;
    // createdDate: Date | string;
  }

  export interface Context {
    company: Model;
    status: ChangeRequestStatus;
  }

  export type POST = Model;

  export type PUT = Model;

  export type PaginatedModelForContext = Page<Context>;

  export type PaginatedModel = Page<Model>;

}
