import {Brand} from "./brand.model";

export namespace Equipment {

    export interface PreModel {
      id: string;
    }

    export interface Model extends PreModel {
      name: string;
      brand: Brand;
      price: number;
    }

}
