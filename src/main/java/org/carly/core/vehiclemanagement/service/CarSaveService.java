package org.carly.core.vehiclemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.service.vehicle_services.VehicleSaveService;
import org.carly.api.rest.CarChangeRequestRest;
import org.carly.api.rest.CarRest;
import org.carly.core.vehiclemanagement.mapper.CarMapper;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.model.CarChangeRequest;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.carly.core.vehiclemanagement.repository.CarRepository;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Service
@Slf4j
public class CarSaveService implements VehicleSaveService<CarRest> {

    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final CarChangeRequestService carChangeRequestService;


    public CarSaveService(CarMapper carMapper,
                          CarRepository carRepository,
                          CarChangeRequestService carChangeRequestService) {
        this.carMapper = carMapper;
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
        Car carToUpdate = carRepository.findById(newCar.getId()).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Car updatedCar = carMapper.mapToDomainObject(carToUpdate, newCar);
        return carMapper.simplifyRestObject(updatedCar);
    }

    @Override
    public void deleteVehicle(ObjectId id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        carRepository.delete(car);
        log.info("Car with id: {} was successful deleted", car.getId());
    }

    public CarRest updatePendingVehicle(CarChangeRequestRest carChangeRequestRest) {
        CarChangeRequest carChangeRequest = carChangeRequestService.updatePendingCar(carChangeRequestRest);
        Car car = carChangeRequest.getCar();
        if (carChangeRequest.getStatus() == ChangeRequestStatus.ACCEPTED) {
            carRepository.save(car);
            log.info("Car with {} was accepted and saved!", car.getId());
        }
        return carMapper.simplifyRestObject(car);
    }
}