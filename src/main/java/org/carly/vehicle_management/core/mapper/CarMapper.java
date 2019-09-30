package org.carly.vehicle_management.core.mapper;

import org.carly.shared.utils.MapperService;
import org.carly.vehicle_management.api.model.CarRest;
import org.carly.vehicle_management.core.model.Car;
import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;

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
        carRest.setBrand(domain.getBrand());
        carRest.setModel(domain.getModel());
        carRest.setMaxSpeed(domain.getMaxSpeed());
        carRest.setPrice(domain.getPrice());
        carRest.setYearOfProduction(domain.getYearOfProduction());
        carRest.setTransmission(domain.getTransmission());
//        carRest.setTires(domain.getTires());
        carRest.setWheels(domain.getWheels());
        carRest.setWindows(domain.getWindows());
//        carRest.setBody(domain.getBody());
        carRest.setWeight(domain.getWeight());
        carRest.setNumberOfDoors(domain.getNumberOfDoors());
        carRest.setBodyPainting(domain.getBodyPainting());
        carRest.setEquipment(domain.getEquipment());
        return carRest;
    }

    @Override
    public Car mapToDomainObject(Car car, CarRest rest) {
//        car.setName(rest.getName());
        car.setBrand(rest.getBrand());
        car.setModel(rest.getModel());
        car.setMaxSpeed(rest.getMaxSpeed());
        car.setPrice(rest.getPrice());
        car.setYearOfProduction(rest.getYearOfProduction());
        car.setTransmission(rest.getTransmission());
        car.setTires(rest.getTires());
        car.setWheels(rest.getWheels());
        car.setWindows(rest.getWindows());
//        car.setBody(carRest.getBody());
        car.setWeight(rest.getWeight());
        car.setNumberOfDoors(rest.getNumberOfDoors());
        car.setBodyPainting(rest.getBodyPainting());
        car.setEquipment(rest.getEquipment());
        return car;
    }
}