import {Page} from "./paginated.model";


export namespace Breaks {

  export interface Model {


  }

  export type PaginatedModel = Page<Model>;

  export type POST = Model;

  export type PUT = Model;


}
