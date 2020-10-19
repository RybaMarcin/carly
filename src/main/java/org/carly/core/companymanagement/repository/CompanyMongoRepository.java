package org.carly.core.companymanagement.repository;

import org.carly.api.rest.CompanySearchCriteriaRest;
import org.carly.core.companymanagement.model.Company;
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
public class CompanyMongoRepository {

    private final MongoTemplate mongoTemplate;

    public CompanyMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Company> findWithFilters(CompanySearchCriteriaRest searchCriteria, Pageable pageable) {


        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Company.NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Company.class);
        List<Company> companies = mongoTemplate.find(query.with(pageable), Company.class);

        return new PageImpl<>(companies, pageable, count);
    }

}
