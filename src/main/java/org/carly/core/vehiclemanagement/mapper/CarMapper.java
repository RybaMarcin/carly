package org.carly.core.vehiclemanagement.mapper;

import org.carly.api.rest.response.BodyResponse;
import org.carly.api.rest.response.BrandResponse;
import org.carly.api.rest.response.CarResponse;
import org.carly.api.rest.partsmanagement.*;
import org.carly.core.partsmanagement.model.*;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.vehiclemanagement.model.Brand;
import org.carly.core.vehiclemanagement.model.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapper implements MapperService<CarResponse, Car> {

    @Override
    public Car simplifyDomainObject(CarResponse rest) {
        Car car = new Car();
        return mapToDomainObject(car, rest);
    }

    @Override
    public CarResponse simplifyRestObject(Car domain) {
        CarResponse rest = new CarResponse();
        return mapFromDomainObject(domain, rest);
    }

    @Override
    public CarResponse mapFromDomainObject(Car domain, CarResponse carResponse) {
        carResponse.setName(domain.getName());
        carResponse.setCode(domain.getCode());
        carResponse.setVinNumber(domain.getVinNumber());
        carResponse.setBrand(new BrandResponse());
        carResponse.getBrand().setId(domain.getBrand().getId());
        carResponse.getBrand().setName(domain.getBrand().getName());
        carResponse.getBrand().setRating(domain.getBrand().getRating());

        carResponse.setModel(new ModelRest());
        carResponse.getModel().setId(domain.getModel().getId());
        carResponse.getModel().setName(domain.getModel().getName());

        carResponse.setMaxSpeed(domain.getMaxSpeed());
        carResponse.setPrice(domain.getPrice());
        carResponse.setYearOfProduction(domain.getYearOfProduction());
        carResponse.setTransmission(domain.getTransmission().getType());
        carResponse.setTires(new TiresRest());
        carResponse.getTires().setId(domain.getTires().getId());

        carResponse.setWheels(new WheelsRest());
        carResponse.getWheels().setId(domain.getWheels().getId());

//        carRest.getWheels().setBrand(new BrandRest());
//        carRest.getWheels().getBrand().setId(domain.getWheels().getBrand().getId());
//        carRest.getWheels().getBrand().setName(domain.getWheels().getBrand().getName());
//        carRest.getWheels().getBrand().setRating(domain.getWheels().getBrand().getRating());

        carResponse.setBody(new BodyResponse());
        carResponse.getBody().setId(domain.getBody().getId());
        carResponse.getBody().setName(domain.getBody().getName());

        carResponse.setWeight(domain.getWeight());
        carResponse.setNumberOfDoors(domain.getNumberOfDoors());

        carResponse.setBodyPainting(new PaintingRest());
        carResponse.getBodyPainting().setId(domain.getBodyPainting().getId());
        carResponse.getBodyPainting().setName(domain.getBodyPainting().getName());
        carResponse.getBodyPainting().setType(domain.getBodyPainting().getType().getType());
        carResponse.setEquipment(mapEquipmentToRest(domain.getEquipment()));

        return carResponse;
    }

    @Override
    public Car mapToDomainObject(Car car, CarResponse rest) {
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
            rest.setBrand(new BrandRequest());
            rest.getBrand().setId(rest.getBrand().getId());
            rest.getBrand().setName(rest.getBrand().getName());
            rest.getBrand().setRating(rest.getBrand().getRating());
            equipmentList.add(rest);
        }
        return equipmentList;
    }
}