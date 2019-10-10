package org.carly.shared.service.vehicle_services;

import org.bson.types.ObjectId;

import java.util.Collection;

public interface VehicleFindService<T> {

    Collection<T> findAll();

    T findVehicleById(ObjectId id);

    T findPendingVehicleById(ObjectId id);

}
