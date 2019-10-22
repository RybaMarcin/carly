package org.carly.vehicle_management.core.mapper;

import org.carly.parts_management.api.model.*;
import org.carly.parts_management.core.model.*;
import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.api.model.*;
import org.carly.vehicle_management.core.model.*;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements MapperService<CarRest, Car> {

    @Override
    public Car simplifyDomainObject(CarRest rest) {
        Car car = new Car();
        return mapToDomainObject(car, rest);
    }

    @Override
    public CarRest simplifyRestObject(Car domain) {
        CarRest rest = new CarRest();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public CarRest mapFromDomainObject(Car domain, CarRest carRest) {
        carRest.setName(domain.getName());
        carRest.setCode(domain.getCode());
        carRest.setVinNumber(domain.getVinNumber());
        carRest.setBrand(new BrandRest());
        carRest.getBrand().setId(domain.getBrand().getId());
        carRest.getBrand().setName(domain.getBrand().getName());
        carRest.getBrand().setRating(domain.getBrand().getRating());

        carRest.setModel(new ModelRest());
        carRest.getModel().setId(domain.getModel().getId());
        carRest.getModel().setName(domain.getModel().getName());

        carRest.setMaxSpeed(domain.getMaxSpeed());
        carRest.setPrice(domain.getPrice());
        carRest.setYearOfProduction(domain.getYearOfProduction());
        carRest.setTransmission(domain.getTransmission().getType());
        carRest.setTires(new TiresRest());
        carRest.getTires().setId(domain.getTires().getId());

        carRest.setWheels(new WheelsRest());
        carRest.getWheels().setId(domain.getWheels().getId());

        carRest.getWheels().setBrand(new BrandRest());
        carRest.getWheels().getBrand().setId(domain.getWheels().getBrand().getId());
        carRest.getWheels().getBrand().setName(domain.getWheels().getBrand().getName());
        carRest.getWheels().getBrand().setRating(domain.getWheels().getBrand().getRating());

        carRest.setBody(new BodyRest());
        carRest.getBody().setId(domain.getBody().getId());
        carRest.getBody().setName(domain.getBody().getName());

        carRest.setWeight(domain.getWeight());
        carRest.setNumberOfDoors(domain.getNumberOfDoors());

        carRest.setBodyPainting(new PaintingRest());
        carRest.getBodyPainting().setId(domain.getBodyPainting().getId());
        carRest.getBodyPainting().setName(domain.getBodyPainting().getName());
        carRest.getBodyPainting().setType(domain.getBodyPainting().getType().getType());

        carRest.setEquipment(new EquipmentRest());
        carRest.getEquipment().setId(domain.getEquipment().getId());
        carRest.getEquipment().setBrand(new BrandRest());
        carRest.getEquipment().getBrand().setId(domain.getEquipment().getBrand().getId());
        carRest.getEquipment().getBrand().setName(domain.getEquipment().getBrand().getName());
        carRest.getEquipment().getBrand().setRating(domain.getEquipment().getBrand().getRating());
        carRest.getEquipment().setName(domain.getEquipment().getName());
        carRest.getEquipment().setPrice(domain.getEquipment().getPrice());

        return carRest;
    }

    @Override
    public Car mapToDomainObject(Car car, CarRest rest) {
        car.setVinNumber(rest.getVinNumber());
        car.setName(rest.getName());
        car.setCode(rest.getCode());
        car.setBrand(new Brand());
        car.getBrand().setId(rest.getBrand().getId());
        car.getBrand().setName(rest.getName());
        car.getBrand().setRating(rest.getBrand().getRating());

        car.setModel(new Model());
        car.getModel().setId(rest.getModel().getId());
        car.getModel().setName(rest.getModel().getName());

        car.setMaxSpeed(rest.getMaxSpeed());
        car.setPrice(rest.getPrice());
        car.setYearOfProduction(rest.getYearOfProduction());

        car.setTransmission(Transmission.of(rest.getTransmission()));

        car.setTires(new Tires());
        car.getTires().setId(rest.getId());

        car.setWheels(new Wheels());
        car.getWheels().setId(rest.getWheels().getId());
        car.getWheels().setBrand(new Brand());
        car.getWheels().getBrand().setId(rest.getWheels().getBrand().getId());
        car.getWheels().getBrand().setName(rest.getWheels().getBrand().getName());
        car.getWheels().getBrand().setRating(rest.getWheels().getBrand().getRating());
        car.getWheels().setName(rest.getWheels().getName());
        car.getWheels().setDiameter(rest.getWheels().getDiameter());

        car.setWindows(new Windows());
        car.getWindows().setId(rest.getWindows().getId());
        car.getWindows().setName(rest.getWindows().getName());
        car.getWindows().setColor(rest.getWindows().getColor());

        car.setBody(new Body());
        car.getBody().setId(rest.getBody().getId());
        car.getBody().setName(rest.getBody().getName());

        car.setWeight(rest.getWeight());
        car.setNumberOfDoors(rest.getNumberOfDoors());

        car.setBodyPainting(new Painting());
        car.getBodyPainting().setId(rest.getBodyPainting().getId());
        car.getBodyPainting().setName(rest.getBodyPainting().getName());
        car.getBodyPainting().setType(PaintType.of(rest.getBodyPainting().getType()));

        car.setEquipment(new Equipment());
        car.getEquipment().setId(rest.getEquipment().getId());
        car.getEquipment().setName(rest.getEquipment().getName());
        car.getEquipment().setBrand(new Brand());
        car.getEquipment().getBrand().setId(rest.getEquipment().getBrand().getId());
        car.getEquipment().getBrand().setName(rest.getEquipment().getBrand().getName());
        car.getEquipment().getBrand().setRating(rest.getEquipment().getBrand().getRating());
        return car;
    }
}