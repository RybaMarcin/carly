import {Roles} from "./roles.model";

export interface User {
  id: string;
  companyId: string;
  name: string;
  email: string;
  role: Roles;
}
