package org.carly.core.partsmanagement.repository;

import org.carly.api.rest.criteria.WheelsSearchCriteriaRequest;
import org.carly.core.partsmanagement.model.Wheels;
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
public class WheelsMongoRepository {




    private final MongoTemplate mongoTemplate;


    public WheelsMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Wheels> findWithFilters(WheelsSearchCriteriaRequest searchCriteria, Pageable pageable) {


        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Wheels.NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Wheels.class);
        List<Wheels> wheels = mongoTemplate.find(query.with(pageable), Wheels.class);

        return new PageImpl<>(wheels, pageable, count);
    }

}
