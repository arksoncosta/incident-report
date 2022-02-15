package com.arkson.incidentreport.coreelasticsearch.criteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElasticSearchCriteriaBuilderTest {

    private ElasticSearchCriteriaBuilder elasticSearchCriteriaBuilder;

    @BeforeEach
    void setUp() {
        elasticSearchCriteriaBuilder = new ElasticSearchCriteriaBuilder();
    }

    @Test
    void buildElasticSearchQueryTest() {
        String query = elasticSearchCriteriaBuilder
                .addCriteria(new CriteriaBuilderTitle("the title"))
                .addCriteria(new CriteriaBuilderDescription("the description"))
                .build();

        assertNotNull(query);
        assertFalse(query.isBlank());

        String expected = "{\"wildcard\":{\"title\":\"the title\"}},{\"wildcard\":{\"description\":\"the description\"}}";
        assertEquals(expected, query);
    }
}