package org.carly.core.shared.service.part_services;

import org.bson.types.ObjectId;

public interface PartSaveService<T> {

    T createPart(T part);

    T updatePart(T part);

    void deletePart(ObjectId id);

}
