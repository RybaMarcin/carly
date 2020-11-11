package org.carly.core.partsmanagement.repository;

import org.carly.api.rest.criteria.WindowsSearchCriteriaRequest;
import org.carly.core.partsmanagement.model.Windows;
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
public class WindowsMongoRepository {

    private final MongoTemplate mongoTemplate;

    public WindowsMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Windows> findWithFilters(WindowsSearchCriteriaRequest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Windows.NAME, searchCriteria.getNameToSearch()));

        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Windows.class);
        List<Windows> windows = mongoTemplate.find(query.with(pageable), Windows.class);

        return new PageImpl<>(windows, pageable, count);

    }


}
