package org.carly.core.vehiclemanagement.repository;

import org.carly.api.rest.CarSearchCriteriaRest;
import org.carly.core.vehiclemanagement.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.carly.core.shared.utils.criteria.CriteriaBuilder.criteria;
import static org.carly.core.shared.utils.criteria.CriteriaBuilder.regexCriteria;

@Repository
public class CarMongoRepository {

    private final MongoTemplate mongoTemplate;

    public CarMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Car> findWithFilters(CarSearchCriteriaRest searchCriteria, Pageable pageable) {


        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Car.NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Car.class);
        List<Car> cars = mongoTemplate.find(query.with(pageable), Car.class);

        return new PageImpl<>(cars, pageable, count);
    }

}
