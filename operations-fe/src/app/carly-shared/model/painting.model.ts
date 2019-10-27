import {Page} from "./paginated.model";

export namespace Painting {

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel {
    type: PaintType;
  }

  export type POST = Model;

  export type PUT = Model;

  export type PaginatedModel = Page<Model>;

  export enum PaintType {
    METALIC = 'Metalic',
    MATTE = 'Matte'
  }

}
