import {Car} from "./car.model";

export namespace ReviewChanges {
  export interface Model {
    carCode: string,
    currentValue: Car.Model;
    modifiedValue: Car.Model;
  }

  export enum ChangeRequestStatus {
    PENDING = "PENDING",
    ACCEPTED = "ACCEPTED",
    DECLINED = "DECLINED"
  }

  export interface PUT {
    id: string;
    status: ChangeRequestStatus;
  }

  export interface ChangeDecision {
    objectId: string;
    status: ChangeRequestStatus;
    declinedReason?: string;
    modifiedByRole?: string;
  }


}
