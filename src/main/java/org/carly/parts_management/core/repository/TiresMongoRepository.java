package org.carly.parts_management.core.repository;

import org.carly.parts_management.api.model.TiresRest;
import org.carly.parts_management.api.model.criteria.TiresSearchCriteriaRest;
import org.carly.parts_management.core.model.Tires;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.carly.shared.utils.criteria.CriteriaBuilder.criteria;
import static org.carly.shared.utils.criteria.CriteriaBuilder.regexCriteria;

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
