package org.carly.core.customermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.response.CustomerResponse;
import org.carly.core.customermanagement.repository.CustomerRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.repository.CarRepository;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class CustomerFindService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerFindService(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    //todo
    public CustomerResponse findCustomerByCarId(ObjectId carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        customerRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return null;
    }
}
