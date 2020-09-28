import {Roles} from "./roles.model";

export interface User {
  id?: string;
  companyId?: string;
  name?: string;
  role?: Roles;
  email: string;
  password: string;
}
