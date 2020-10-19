package org.carly.core.vehiclemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.carly.core.shared.config.EntityAlreadyExistsException;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.api.rest.CarChangeRequestRest;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.model.CarChangeRequest;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.carly.core.vehiclemanagement.repository.CarChangeRequestRepository;
import org.carly.core.vehiclemanagement.repository.CarRepository;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

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

    Car saveCarChangeRequest(Car car) {
        Car findCarByVinNumberAndStatus = carRepository.findByVinNumberAndRequestStatus(car.getVinNumber(), ChangeRequestStatus.PENDING);
        if (findCarByVinNumberAndStatus == null) {
            CarChangeRequest requestToSave = createCarChangeRequest(car);
            carChangeRequestRepository.save(requestToSave);
            return car;
        }
        log.error("Entity exists and waiting for change request!");
        throw new EntityAlreadyExistsException("Entity already exists!");
    }

    private CarChangeRequest createCarChangeRequest(Car car) {
        CarChangeRequest result = new CarChangeRequest();
        car.setRequestStatus(ChangeRequestStatus.PENDING);
        car.setCreateAt(timeService.getLocalDate());

        carRepository.save(car);
        result.setCar(car);
        result.setCreateDate(timeService.getLocalDate());
        result.setStatus(ChangeRequestStatus.PENDING);
        result.setLastModificationDate(timeService.getLocalDate());
        return result;
    }

    CarChangeRequest updatePendingCar(CarChangeRequestRest changeDecision) {
        CarChangeRequest carChangeRequest = carChangeRequestRepository.findByIdAndStatus(changeDecision.getCarId(),
                ChangeRequestStatus.PENDING).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        carChangeRequest.setStatus(ChangeRequestStatus.valueOf(changeDecision.getChangeRequestStatus()));
        carChangeRequest.setStatusChangeReason(changeDecision.getDeclinedReason() != null ? changeDecision.getDeclinedReason() : "");
        carChangeRequest.setLastModificationDate(timeService.getLocalDate());
        carChangeRequestRepository.save(carChangeRequest);
        return carChangeRequest;
    }
}