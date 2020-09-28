package org.carly.vehicle_management.core.mapper;

import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.api.model.*;
import org.carly.vehicle_management.core.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

//        carRest.getWheels().setBrand(new BrandRest());
//        carRest.getWheels().getBrand().setId(domain.getWheels().getBrand().getId());
//        carRest.getWheels().getBrand().setName(domain.getWheels().getBrand().getName());
//        carRest.getWheels().getBrand().setRating(domain.getWheels().getBrand().getRating());

        carRest.setBody(new BodyRest());
        carRest.getBody().setId(domain.getBody().getId());
        carRest.getBody().setName(domain.getBody().getName());

        carRest.setWeight(domain.getWeight());
        carRest.setNumberOfDoors(domain.getNumberOfDoors());

        carRest.setBodyPainting(new PaintingRest());
        carRest.getBodyPainting().setId(domain.getBodyPainting().getId());
        carRest.getBodyPainting().setName(domain.getBodyPainting().getName());
        carRest.getBodyPainting().setType(domain.getBodyPainting().getType().getType());
        carRest.setEquipment(mapEquipmentToRest(domain.getEquipment()));

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
//        car.getWheels().setBrand(new Brand());
//        car.getWheels().getBrand().setId(rest.getWheels().getBrand().getId());
//        car.getWheels().getBrand().setName(rest.getWheels().getBrand().getName());
//        car.getWheels().getBrand().setRating(rest.getWheels().getBrand().getRating());
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
        car.setEquipment(mapEquipmentFromRest(rest.getEquipment()));
        return car;
    }

    private List<Equipment> mapEquipmentFromRest(List<EquipmentRest> restList) {
        List<Equipment> equipmentList = new ArrayList<>();
        for (EquipmentRest rest : restList) {
            Equipment domain = new Equipment();
            domain.setId(rest.getId());
            domain.setName(rest.getName());
            domain.setBrand(new Brand());
            domain.getBrand().setId(rest.getBrand().getId());
            domain.getBrand().setName(rest.getBrand().getName());
            domain.getBrand().setRating(rest.getBrand().getRating());
            equipmentList.add(domain);
        }
        return equipmentList;
    }

    private List<EquipmentRest> mapEquipmentToRest(List<Equipment> restList) {
        List<EquipmentRest> equipmentList = new ArrayList<>();
        for (Equipment domain : restList) {
            EquipmentRest rest = new EquipmentRest();
            rest.setId(domain.getId());
            rest.setName(domain.getName());
            rest.setBrand(new BrandRest());
            rest.getBrand().setId(rest.getBrand().getId());
            rest.getBrand().setName(rest.getBrand().getName());
            rest.getBrand().setRating(rest.getBrand().getRating());
            equipmentList.add(rest);
        }
        return equipmentList;
    }
}