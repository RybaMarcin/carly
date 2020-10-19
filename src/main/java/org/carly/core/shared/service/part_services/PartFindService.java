package org.carly.core.shared.service.part_services;

import org.bson.types.ObjectId;

import java.util.Collection;

public interface PartFindService<T> {

    Collection<T> findAll();

    T findPartById(ObjectId id);

}
