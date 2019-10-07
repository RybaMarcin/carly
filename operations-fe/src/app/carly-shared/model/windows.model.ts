export namespace Windows {

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel {
    color: string;
  }

}
