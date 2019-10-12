import {Brand} from "./brand.model";
import {Page} from "./paginated.model";

export namespace Equipment {

    export interface PreModel {
      id: string;
    }

    export interface Model extends PreModel {
      name: string;
      brand: Brand;
      type: EquipmentType;
      price: number;
    }

    export type POST = Model;

    export type PUT = Model;

    export type PaginatedModel = Page<Model>;

    export enum EquipmentType {
      Radio = "Radio",

    }

}
