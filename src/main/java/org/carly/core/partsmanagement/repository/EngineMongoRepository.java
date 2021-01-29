package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.api.rest.criteria.EngineSearchCriteriaRequest;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.carly.core.vehiclemanagement.model.Factory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static org.carly.core.shared.utils.criteria.CriteriaBuilder.criteria;
import static org.carly.core.shared.utils.criteria.CriteriaBuilder.regexCriteria;

@Repository
public class EngineMongoRepository {

    private static final String FACTORY_ID = Engine.FACTORY + "." + Factory.CARLY_FACTORY_ID;

    private final MongoTemplate mongoTemplate;


    public EngineMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Engine> findWithFilters(EngineSearchCriteriaRequest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Engine.NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Engine.class);
        List<Engine> engines = mongoTemplate.find(query.with(pageable), Engine.class);

        return new PageImpl<>(engines, pageable, count);
    }

    public Collection<Engine> findEnginesWithFactoryIdInList(List<ObjectId> factoryIds) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                            criteria(FACTORY_ID, Criteria::in, factoryIds));

        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Engine.class);
    }
}
