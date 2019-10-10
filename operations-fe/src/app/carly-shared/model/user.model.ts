import {Roles} from "./roles.model";

export interface User {
  id: string;
  ownerId: string;
  companyId: string;
  name: string;
  email: string;
  role: Roles;
}
