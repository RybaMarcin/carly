export namespace Painting {

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel {
    type: PaintType;
  }

  export enum PaintType {
    METALIC = 'Metalic',
    MATTE = 'Matte'
  }

}
