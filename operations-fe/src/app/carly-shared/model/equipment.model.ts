import {Brand} from "./brand.model";
import {Page} from "./paginated.model";

export namespace Equipment {

    export interface PreModel {
      id: string;
      name: string;
      brand: Brand;
      preview: string;
      price: number;
      createdDate: Date | string;
    }

    export interface Model extends PreModel {
      type: EquipmentType;
    }

    export type POST = Model;

    export type PUT = Model;

    export type PaginatedModel = Page<Model>;

    export enum EquipmentType {
      Radio = "Radio",
      GPS = "GPS",
      DVD = "DVD",
      MP3 = "MP3"
    }

}
