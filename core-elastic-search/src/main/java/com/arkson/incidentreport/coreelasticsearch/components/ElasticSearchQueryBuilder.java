package com.arkson.incidentreport.coreelasticsearch.components;

import com.arkson.incidentreport.coreelasticsearch.criteria.*;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;

public final class ElasticSearchQueryBuilder {

    public static String generateElasticSearchQuery(IncidentSearchVO incidentSearchVO) {
        ElasticSearchCriteriaBuilder esBuilder =
                new ElasticSearchCriteriaBuilder()
                        .addCriteria(new CriteriaBuilderTitle(incidentSearchVO.getTitle()))
                        .addCriteria(new CriteriaBuilderDescription(incidentSearchVO.getDescription()))
                        .addCriteria(new CriteriaBuilderCreatedDate(incidentSearchVO.getInitialDateTime(), incidentSearchVO.getFinalDateTime()));

        String query = String.format("{\"query\": {\"bool\": {\"must\": [%s]}}}", esBuilder.build());

        return query;
    }

    public static String buildElasticSearchQueryId(String id) {
        ElasticSearchCriteriaBuilder esBuilder =
                new ElasticSearchCriteriaBuilder()
                        .addCriteria(new CriteriaBuilderId(id));

        String query = String.format("{\"query\": {\"bool\": {\"must\": [%s]}}}", esBuilder.build());

        return query;
    }

}
