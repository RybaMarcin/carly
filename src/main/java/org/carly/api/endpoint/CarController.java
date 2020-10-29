package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.CarChangeRequestRest;
import org.carly.api.rest.CarRest;
import org.carly.api.rest.CarSearchCriteriaRest;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.service.CarFindService;
import org.carly.core.vehiclemanagement.service.CarSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarFindService carFindService;
    private final CarSaveService carSaveService;

    public CarController(CarFindService carFindService, CarSaveService carSaveService) {
        this.carFindService = carFindService;
        this.carSaveService = carSaveService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<CarRest> findCars(CarSearchCriteriaRest searchCriteria, Pageable pageable){
        return carFindService.findCars(searchCriteria, pageable);
    }

    @GetMapping("/cars/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Car findCarById(@PathVariable("id") String id) {
        return carFindService.findVehicleById(new ObjectId(id));
    }

    @GetMapping("/cars/all")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Collection<CarRest> findAllCars() {
        return carFindService.findAll();
    }

    @GetMapping("/cars/pending/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public CarRest findPendingCar(@PathVariable("id") String id) {
        return carFindService.findPendingVehicleById(new ObjectId(id));
    }

    @PostMapping("/create-car")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public CarRest createCar(CarRest car) {
        return carSaveService.createVehicle(car);
    }

    @PostMapping("/cars/pending")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public CarRest savePendingCar(CarChangeRequestRest changeDecision) {
        return carSaveService.updatePendingVehicle(changeDecision);
    }

    @PutMapping("/cars")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY')")
    public CarRest updateCar(CarRest car) {
        return carSaveService.updateVehicle(car);
    }
}