package com.arkson.incidentreport.coreelasticsearch.criteria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CriteriaBuilderDescription extends CriteriaBuilder {

    public CriteriaBuilderDescription(String description) {
        if (description != null && !description.isBlank()) {
            super.values.add(description);
        }
    }

    @Override
    protected String criteriaString() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("wildcard")
                .put("description", super.values.get(0).toString());

        return objectNode.toString();
    }
}
