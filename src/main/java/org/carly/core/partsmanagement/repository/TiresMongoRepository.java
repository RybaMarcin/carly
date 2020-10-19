package org.carly.core.partsmanagement.repository;

import org.carly.api.rest.partsmanagement.criteria.TiresSearchCriteriaRest;
import org.carly.core.partsmanagement.model.Tires;
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
public class TiresMongoRepository {

    private final MongoTemplate mongoTemplate;

    public TiresMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Tires> findWithFilters(TiresSearchCriteriaRest searchCriteria,
                                           Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Tires.NAME, searchCriteria.getNameToSearch()));

        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Tires.class);
        List<Tires> tires = mongoTemplate.find(query.with(pageable), Tires.class);

        return new PageImpl<>(tires, pageable, count);

    }

}
