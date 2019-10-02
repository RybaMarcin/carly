package org.carly.vehicle_management.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.vehicle_management.api.model.CarChangeRequestRest;
import org.carly.vehicle_management.api.model.CarRest;
import org.carly.vehicle_management.api.model.CarSearchCriteriaRest;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.service.CarFindService;
import org.carly.vehicle_management.core.service.CarSaveService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

@RestController("/cars")
@Slf4j
public class CarController {

    private final CarFindService carFindService;
    private final CarSaveService carSaveService;


    public CarController(CarFindService carFindService, CarSaveService carSaveService) {
        this.carFindService = carFindService;
        this.carSaveService = carSaveService;
    }

    @GetMapping("/cars")
    public List<CarRest> findCars(CarSearchCriteriaRest searchCriteria, Page pageable){
        return carFindService.findCars(searchCriteria, pageable);
    }

    @GetMapping("/cars/{id}")
    public Car findCarById(@PathVariable("id") ObjectId id) {
        return carFindService.findVehicleById(id);
    }

    @GetMapping("/cars/all")
    public Collection<CarRest> findAllCars() {
        return carFindService.findAll();
    }

    @GetMapping("/cars/pending/{id}")
    public CarRest findPendingCar(@PathVariable("id") ObjectId id) {
        return carFindService.findPendingVehicleById(id);
    }

    @PostMapping("/create")
    public CarRest createCar(CarRest car) {
        return carSaveService.createVehicle(car);
    }

    @PostMapping("/cars/pending")
    public CarRest savePendingCar(CarChangeRequestRest changeDecision) {
        return carSaveService.updatePendingVehicle(changeDecision);
    }

    @PutMapping("/cars")
    public CarRest updateCar(CarRest car) {
        return carSaveService.updateVehicle(car);
    }
}