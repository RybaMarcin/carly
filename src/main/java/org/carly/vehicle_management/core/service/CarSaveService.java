package org.carly.vehicle_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.service.vehicle_services.VehicleSaveService;
import org.carly.vehicle_management.api.model.CarChangeRequestRest;
import org.carly.vehicle_management.api.model.CarRest;
import org.carly.vehicle_management.core.mapper.CarMapper;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.CarChangeRequest;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.carly.vehicle_management.core.repository.CarRepository;
import org.springframework.stereotype.Service;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

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