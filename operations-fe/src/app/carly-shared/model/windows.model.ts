import {Brand} from "./brand.model";
import {Page} from "./paginated.model";

export namespace Windows {

  export interface PreModel {
    id: string;
    name: string;
    brand: Brand;
    preview: string;
    price: number;
    createdDate: Date | string;
  }

  export interface Model extends PreModel {
    color: string;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;

}
