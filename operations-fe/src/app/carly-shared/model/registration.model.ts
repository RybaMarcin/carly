export namespace Registration {

  export interface Model {
    name: string;
    lastName: string;
    email: string;
    password: string;
    matchingPassword: string;
  }

  export type POST = Model;

}
