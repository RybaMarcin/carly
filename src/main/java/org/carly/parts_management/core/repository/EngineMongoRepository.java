package org.carly.parts_management.core.repository;

import org.carly.parts_management.api.model.criteria.EngineSearchCriteriaRest;
import org.carly.parts_management.core.model.Engine;
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
public class EngineMongoRepository {

    private final MongoTemplate mongoTemplate;


    public EngineMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Engine> findWithFilters(EngineSearchCriteriaRest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Engine.PART_NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Engine.class);
        List<Engine> engines = mongoTemplate.find(query.with(pageable), Engine.class);

        return new PageImpl<>(engines, pageable, count);
    }

}
