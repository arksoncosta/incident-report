package com.arkson.incidentreport.coreelasticsearch.criteria;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticSearchCriteriaBuilder {

    private List<CriteriaBuilder> query = new LinkedList<>();

    public ElasticSearchCriteriaBuilder addCriteria(CriteriaBuilder criteriaBuilder) {
        if (!criteriaBuilder.values.isEmpty())
            query.add(criteriaBuilder);

        return this;
    }

    public String build() {
        return query
                .stream()
                .map(CriteriaBuilder::criteriaString)
                .collect(Collectors.joining(","));
    }
}
