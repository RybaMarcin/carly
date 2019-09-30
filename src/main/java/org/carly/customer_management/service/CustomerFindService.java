package org.carly.customer_management.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.customer_management.api.CustomerRest;
import org.carly.customer_management.model.Customer;
import org.carly.customer_management.repository.CustomerRepository;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.repository.CarRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerFindService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerFindService(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public CustomerRest findCustomerByCarId(ObjectId carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        customerRepository.findById()
    }
}
