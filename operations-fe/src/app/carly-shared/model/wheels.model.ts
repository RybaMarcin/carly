import {Brand} from "./brand.model";
import {Page} from "./paginated.model";
import {Part} from "./part.model";

export namespace Wheels {

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel, Part.Model {
    brand: Brand;
    diameter: number;
    weight: number;
    price: number;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;
}
