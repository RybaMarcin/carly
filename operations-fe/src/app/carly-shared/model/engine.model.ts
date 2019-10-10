import {Brand} from "./brand.model";
import {Page} from "./paginated.model";

export namespace Engine {

  export interface PreModel {
    id: string;
  }

  export interface Model extends PreModel {
    name: string;
    brand: Brand;
    horsePower: number;
    weight: number;
    capacity: number;
    numberOfCylinders: number;
    price: number;
    createDate: Date | string;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;

  export enum Cylinders {
    V4 = "V4",
    V6 = "V6",
    V8 = "V8"
  }

}
