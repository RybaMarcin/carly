import {Page} from "./paginated.model";

export namespace Tires {

  export interface Model {
    name: string;
    price: number;
  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;

  export enum TireType {
      REINFORCED = 'Reinforced',
      ALL_ROAD = 'All road'
  }

}
