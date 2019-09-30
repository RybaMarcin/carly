package org.carly.shared.service.vehicle_services;

import org.bson.types.ObjectId;

public interface VehicleSaveService<T> {

    T createVehicle(T vehicle);

    T updateVehicle(T newCar);

    T deleteVehicle(ObjectId id);

}
