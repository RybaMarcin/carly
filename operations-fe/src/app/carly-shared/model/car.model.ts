import {Painting} from "./painting.model";
import {Equipment} from "./equipment.model";
import {Windows} from "./windows.model";
import {Transmission} from "./transmission.enum";
import {Tires} from "./tires.model";
import {Wheels} from "./wheels.model";
import {CarBody} from "./car-body.model";
import {Engine} from "./engine.model";
import {Brand} from "./brand.model";
import {Page} from "./paginated.model";

export namespace Car {

  export interface PreModel {
    id: string;
    name: string;
  }

  export interface Model extends PreModel {
    brand: Brand;
    model: Model;
    maxSpeed: number;
    accelerate: number;
    price: number;
    yearOfProduction: Date;
    engine: Engine.Model;
    transmission: Transmission;
    tires: Tires;
    wheels: Wheels.Model;
    windows: Windows.Model;
    carBody: CarBody;
    weight: number;
    numberOfDoors: number;
    bodyPainting: Painting.Model;
    leasingAvailable: boolean;
    equipment: Equipment.Model;
  }

  export type PaginatedModel = Page<Model>

  export type POST = Model;

  export type PUT = Model;

  export enum CarType {

  }

}
