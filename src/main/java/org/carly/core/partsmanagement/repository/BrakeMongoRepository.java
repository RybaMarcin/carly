package org.carly.core.partsmanagement.repository;

import org.carly.api.rest.criteria.BreaksSearchCriteriaRequest;
import org.carly.core.partsmanagement.model.entity.Brake;
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
public class BrakeMongoRepository {


    private final MongoTemplate mongoTemplate;

    public BrakeMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Brake> findWithFilters(BreaksSearchCriteriaRequest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Brake.NAME, searchCriteria.getNameToSearch()));

        Query query = new Query();
        query.addCriteria(criteria);


        long count = mongoTemplate.count(query, Brake.class);
        List<Brake> aBrakes = mongoTemplate.find(query.with(pageable), Brake.class);

        return new PageImpl<>(aBrakes, pageable, count);
    }

}
