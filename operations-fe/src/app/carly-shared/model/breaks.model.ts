import {Page} from "./paginated.model";
import {Brand} from "./brand.model";


export namespace Breaks {

  export interface PreModel {
    id: string;
    name: string;
    brand: Brand;
    preview: string;
    price: number;
    createdDate: Date | string;
  }

  export interface Model extends PreModel {

    breaksType: string;

  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;

  export enum BreaksType {
    HYDRAULIC = 'Hydraulic',
    MECHANIC = 'Mechanic',
    PNEUMATIC = 'Pneumatic'
  }

}
