package org.carly.vehicle_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.shared.service.vehicle_services.VehicleSaveService;
import org.carly.shared.utils.TimeService;
import org.carly.vehicle_management.api.model.CarChangeRequestRest;
import org.carly.vehicle_management.api.model.CarRest;
import org.carly.vehicle_management.core.mapper.CarMapper;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarSaveService implements VehicleSaveService<CarRest> {

    private final CarMapper carMapper;
    private final TimeService timeService;
    private final CarRepository carRepository;
    private final CarChangeRequestService carChangeRequestService;


    public CarSaveService(CarMapper carMapper, TimeService timeService, CarRepository carRepository, CarChangeRequestService carChangeRequestService) {
        this.carMapper = carMapper;
        this.timeService = timeService;
        this.carRepository = carRepository;
        this.carChangeRequestService = carChangeRequestService;
    }

    @Override
    public CarRest createVehicle(CarRest carRest) {
        Car car = carMapper.simplifyDomainObject(carRest);
        Car carChangeRequest = carChangeRequestService.saveCarChangeRequest(car);
        return carMapper.simplifyRestObject(carChangeRequest);
    }

    @Override
    public CarRest updateVehicle(CarRest newCar) {
        Car carToUpdate = carRepository.findById(newCar.getId());
        Car updatedCar = carMapper.mapToDomainObject(carToUpdate, newCar);
        return carMapper.simplifyRestObject(updatedCar);
    }

    @Override
    public CarRest deleteVehicle(ObjectId id) {
        return null;
    }

    public CarRest updatePendingVehicle(CarChangeRequestRest carChangeRequest) {
        Car car = carChangeRequestService.updatePendingCar(carChangeRequest);
        carRepository.save(car);
        return carMapper.simplifyRestObject(car);
    }
}
