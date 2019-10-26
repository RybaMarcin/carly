import {Roles} from "./roles.model";

export interface NavLink {
  label: string;
  path: string;
  allowed: Roles[];
}
