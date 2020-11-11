package org.carly.core.vehiclemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.shared.service.vehicle_services.VehicleFindService;
import org.carly.api.rest.response.CarResponse;
import org.carly.api.rest.criteria.CarSearchCriteriaRequest;
import org.carly.core.vehiclemanagement.mapper.CarMapper;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.carly.core.vehiclemanagement.repository.CarMongoRepository;
import org.carly.core.vehiclemanagement.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class CarFindService implements VehicleFindService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final CarMongoRepository carMongoRepository;

    public CarFindService(CarMapper carMapper,
                          CarRepository carRepository,
                          CarMongoRepository carMongoRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
        this.carMongoRepository = carMongoRepository;
    }

    @Override
    public Collection<CarResponse> findAll() {
        List<Car> carList = carRepository.findAll();
        log.info("Car list contains: {}", carList.size());
        return carList.stream().map(carMapper::simplifyRestObject).collect(Collectors.toList());
    }

    @Override
    public Car findVehicleById(ObjectId id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (car != null && car.getRequestStatus() == ChangeRequestStatus.ACCEPTED) {
            log.info("Car with id found :{}", car);
            return car;
        }
        assert car != null;
        log.error("Car with id: {}, not exists or is not ACCEPTED!: {}", id, car.getRequestStatus());
        throw new IllegalArgumentException();
    }

    @Override
    public CarResponse findPendingVehicleById(ObjectId id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        if (car.getRequestStatus() != ChangeRequestStatus.PENDING) {
            log.warn("Car with id: {}, was found but state is not pending!", id);
            return carMapper.simplifyRestObject(car);
        }
        return carMapper.simplifyRestObject(car);
    }

    public Page<CarResponse> findCars(CarSearchCriteriaRequest searchCriteria, Pageable pageable) {
        return carMongoRepository.findWithFilters(searchCriteria, pageable)
                .map(carMapper::simplifyRestObject);
    }
}