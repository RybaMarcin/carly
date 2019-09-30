package org.carly.vehicle_management.core.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.carly.vehicle_management.api.model.CarChangeRequestRest;
import org.carly.vehicle_management.api.model.ChangeRequestStatusRest;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.CarChangeRequest;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.carly.vehicle_management.core.repository.CarChangeRequestRepository;
import org.carly.vehicle_management.core.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CarChangeRequestService {

    private final CarChangeRequestRepository carChangeRequestRepository;
    private final CarRepository carRepository;

    public CarChangeRequestService(CarChangeRequestRepository carChangeRequestRepository, CarRepository carRepository) {
        this.carChangeRequestRepository = carChangeRequestRepository;
        this.carRepository = carRepository;
    }


    public Car saveCarChangeRequest(Car car) {
        PanacheQuery<Car> findCarByVinNumberAndStatus = findByVinAndStatus(car.getVinNumber(), ChangeRequestStatus.PENDING);
        if (findCarByVinNumberAndStatus.firstResult() == null) {
            CarChangeRequest requestToSave = createCarChangeRequest(car, ChangeRequestStatus.PENDING);
            carChangeRequestRepository.persistAndFlush(requestToSave);
            return car;
        }
        log.error("Entity exists and waiting for change request!");
        throw new EntityExistsException();
    }

    public PanacheQuery<Car> findByVinAndStatus(String vinNumber, ChangeRequestStatus status) {
        return carRepository.find("vinNumber = :vinNumber and requestStatus = :requestStatus",
                Parameters.with("vinNumber", vinNumber).and("requestStatus", status).map());
    }

    private CarChangeRequest createCarChangeRequest(Car car, ChangeRequestStatus status) {
        LocalDate now = LocalDate.now();
        CarChangeRequest result = new CarChangeRequest();
        car.setRequestStatus(ChangeRequestStatus.PENDING);
        car.setCreateAt(now);

        carRepository.persistAndFlush(car);
        result.setCar(car);
        result.setCreateDate(now);
        result.setStatus(status);
        result.setLastModificationDate(now);
        return result;
    }

    public CarChangeRequest update(CarChangeRequest carChangeRequest, CarChangeRequestRest request) {
        CarChangeRequest carForUpdate = carChangeRequestRepository.findById(carChangeRequest.getId());

        carForUpdate.setId(carChangeRequest.getId());
        carForUpdate.setLastModificationDate(LocalDate.now());
        carChangeRequestRepository.persistAndFlush(carForUpdate);
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