package com.arkson.incidentreport.coreelasticsearch.criteria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CriteriaBuilderCreatedDate extends CriteriaBuilder {

    public CriteriaBuilderCreatedDate(String initialDateTime, String finalDateTime) {
        super.values.add(initialDateTime);
        super.values.add(finalDateTime);
    }

    @Override
    protected String criteriaString() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("range")
                .putObject("createdDate")
                .put("lte", super.values.get(1).toString())
                .put("gte", super.values.get(0).toString());

        return objectNode.toString();
    }
}
