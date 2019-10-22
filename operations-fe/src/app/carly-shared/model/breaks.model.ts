import {Page} from "./paginated.model";
import {Brand} from "./brand.model";


export namespace Breaks {

  export interface Model {
    name: string;
    brand: Brand;
    preview: string;
    breaksType: string;
    price: number;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;

  export enum BreaksType {

  }

}
