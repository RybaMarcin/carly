import {Roles} from "../../model/roles.model";

export interface NavLink {
  label: string;
  path: string;
  allowed: Roles[];
}
