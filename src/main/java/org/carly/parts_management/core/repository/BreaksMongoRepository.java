package org.carly.parts_management.core.repository;

import org.carly.parts_management.api.model.criteria.BreaksSearchCriteriaRest;
import org.carly.parts_management.core.model.Breaks;
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
public class BreaksMongoRepository {


    private final MongoTemplate mongoTemplate;

    public BreaksMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Breaks> findWithFilters(BreaksSearchCriteriaRest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Breaks.NAME, searchCriteria.getNameToSearch()));

        Query query = new Query();
        query.addCriteria(criteria);


        long count = mongoTemplate.count(query, Breaks.class);
        List<Breaks> breaks = mongoTemplate.find(query.with(pageable), Breaks.class);

        return new PageImpl<>(breaks, pageable, count);
    }

}
