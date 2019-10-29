export namespace Registration {

  export interface Model {
    firstName?: string;
    lastName?: string;
    email: string;
    password: string;
    matchingPassword: string;
    gender?: Gender;
  }

  export type POST = Model;

  export enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE'
  }

}
