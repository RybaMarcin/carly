package org.carly.vehicle_management.core.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.shared.config.EntityAlreadyExistsException;
import org.carly.shared.config.EntityNotFoundException;
import org.carly.shared.utils.TimeService;
import org.carly.vehicle_management.api.model.CarChangeRequestRest;
import org.carly.vehicle_management.api.model.ChangeRequestStatusRest;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.CarChangeRequest;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.carly.vehicle_management.core.repository.CarChangeRequestRepository;
import org.carly.vehicle_management.core.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.carly.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class CarChangeRequestService {

    private final CarChangeRequestRepository carChangeRequestRepository;
    private final CarRepository carRepository;
    private final TimeService timeService;

    public CarChangeRequestService(CarChangeRequestRepository carChangeRequestRepository,
                                   CarRepository carRepository,
                                   TimeService timeService) {
        this.carChangeRequestRepository = carChangeRequestRepository;
        this.carRepository = carRepository;
        this.timeService = timeService;
    }

    public Car saveCarChangeRequest(Car car) {
        Car findCarByVinNumberAndStatus = carRepository.findByVinNumberAndRequestStatus(car.getVinNumber(), ChangeRequestStatus.PENDING);
        if (findCarByVinNumberAndStatus == null) {
            CarChangeRequest requestToSave = createCarChangeRequest(car, ChangeRequestStatus.PENDING);
            carChangeRequestRepository.save(requestToSave);
            return car;
        }
        log.error("Entity exists and waiting for change request!");
        throw new EntityAlreadyExistsException("Entity already exists!");
    }

    private CarChangeRequest createCarChangeRequest(Car car, ChangeRequestStatus status) {

        CarChangeRequest result = new CarChangeRequest();
        car.setRequestStatus(ChangeRequestStatus.PENDING);
        car.setCreateAt(timeService.getLocalDate());

        carRepository.save(car);
        result.setCar(car);
        result.setCreateDate(timeService.getLocalDate());
        result.setStatus(status);
        result.setLastModificationDate(timeService.getLocalDate());
        return result;
    }

    public CarChangeRequest update(CarChangeRequest carChangeRequest, CarChangeRequestRest request) {
        CarChangeRequest carForUpdate = carChangeRequestRepository.findById(carChangeRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));

        carForUpdate.setId(carChangeRequest.getId());
        carForUpdate.setLastModificationDate(LocalDate.now());
        carChangeRequestRepository.save(carForUpdate);
        return carForUpdate;
    }

    public Car updatePendingCar(CarChangeRequestRest changeDecision) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", changeDecision.getCarId());
        params.put("status", ChangeRequestStatus.PENDING);
        CarChangeRequest carChangeRequest = carChangeRequestRepository.find("id = :id and status= :status", params).singleResult();
        update(carChangeRequest, changeDecision);
        if (changeDecision.getChangeRequestStatus() == ChangeRequestStatusRest.ACCEPTED) {
            return carChangeRequest.getCar();
        } else throw new IllegalArgumentException();
    }
}