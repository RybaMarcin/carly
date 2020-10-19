package org.carly.core.partsmanagement.repository;

import org.carly.api.rest.partsmanagement.criteria.EquipmentSearchCriteriaRest;
import org.carly.core.partsmanagement.model.Equipment;
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
public class EquipmentMongoRepository {

    private final MongoTemplate mongoTemplate;


    public EquipmentMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Page<Equipment> findWithFilters(EquipmentSearchCriteriaRest searchCriteria, Pageable pageable) {

        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                regexCriteria(Equipment.NAME, searchCriteria.getNameToSearch()));


        Query query = new Query();
        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Equipment.class);
        List<Equipment> equipment = mongoTemplate.find(query.with(pageable), Equipment.class);

        return new PageImpl<>(equipment, pageable, count);
    }


}
