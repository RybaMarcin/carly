import {Brand} from "./brand.model";

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

}
