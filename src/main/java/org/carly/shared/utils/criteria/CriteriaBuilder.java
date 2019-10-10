package org.carly.shared.utils.criteria;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

public class CriteriaBuilder {

    private static final String REGEX_OPTIONS = "i";

    public static String createPattern(List<String> values) {
        if (!values.isEmpty()) {
            return String.join("|", values);
        }
        return "";
    }

    public static <T> Criteria criteria(String fieldName, BiFunction<Criteria, T, Criteria> criteriaConsumer, T expectedValue) {
        if (expectedValue != null) {
            return criteriaConsumer.apply(Criteria.where(fieldName), expectedValue);
        }
        return null;
    }

    public static Criteria criteria(BiFunction<Criteria, Criteria[], Criteria> criteriaConsumer, Criteria... criteriasToAggregate) {
        return criteria((Criteria) null, criteriaConsumer, criteriasToAggregate);
    }

    public static Criteria criteria(Criteria criteria, BiFunction<Criteria, Criteria[], Criteria> criteriaConsumer, Criteria... criteriasToAggregate) {
        var notNullCriterias = Stream.of(criteriasToAggregate).filter(Objects::nonNull).collect(Collectors.toList());

        if (!notNullCriterias.isEmpty()) {
            criteria = criteriaConsumer.apply(criteria != null ? criteria : new Criteria(), notNullCriterias.toArray(new Criteria[notNullCriterias.size()]));
        }

        return criteria;
    }

    public static Criteria criteria(Criteria criteria, BiFunction<Criteria, Criteria[], Criteria> criteriaConsumer, Collection<Criteria> criteriasToAggregate) {
        var notNullCriterias = criteriasToAggregate.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (!notNullCriterias.isEmpty()) {
            criteria = criteriaConsumer.apply(criteria != null ? criteria : new Criteria(), notNullCriterias.toArray(new Criteria[notNullCriterias.size()]));
        }

        return criteria;
    }

    public static Criteria regexCriteria(String fieldName, List<String> expectedValue) {
        return regexCriteria(fieldName, expectedValue, REGEX_OPTIONS);
    }

    public static Criteria regexCriteria(String fieldName, List<String> expectedValue, String regexOptions) {
        if (!CollectionUtils.isEmpty(expectedValue)) {
            return Criteria.where(fieldName).regex(createPattern(expectedValue), regexOptions);
        }
        return null;
    }

    public static SkipOperation skipToPage(Pageable pageable) {
        return skip((long) (pageable.getPageNumber() * pageable.getPageSize()));
    }

    public static String path(String... fieldName) {
        return String.join(".", fieldName);
    }
}

