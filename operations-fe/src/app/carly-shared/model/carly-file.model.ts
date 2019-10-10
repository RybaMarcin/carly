export enum AccessLevel {
  PUBLIC = "PUBLIC",
  FOR_CUSTOMERS = "FOR_CUSTOMERS",
  FOR_MATCHED_COMPANIES = "FOR_MATCHED_COMPANIES",
  PRIVATE = "PRIVATE",
  CUSTOM = "CUSTOM"
}

export enum UserType {
  CUSTOMER = "CUSTOMER",
  COMPANY = "COMPANY"
}

export class CarlyFile {
  id: string;
  size: string;
  name: string;
}
