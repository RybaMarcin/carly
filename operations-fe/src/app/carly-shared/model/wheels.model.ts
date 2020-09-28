import {Brand} from "./brand.model";
import {Page} from "./paginated.model";
import {Part} from "./part.model";

export namespace Wheels {

  export interface PreModel {
    id: string;
    name: string;
    brand: Brand;
    preview: string;
    price: number;
    createdDate: Date | string;
  }

  export interface Model extends PreModel {
    diameter: number;
    weight: number;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;
}
