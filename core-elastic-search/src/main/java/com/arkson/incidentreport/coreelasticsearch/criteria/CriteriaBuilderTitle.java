package com.arkson.incidentreport.coreelasticsearch.criteria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CriteriaBuilderTitle extends CriteriaBuilder {

    private String title;

    public CriteriaBuilderTitle(String title) {
        if (title != null && !title.isBlank()) {
            super.values.add(title);
        }
    }

    @Override
    protected String criteriaString() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("wildcard")
                .put("title", super.values.get(0).toString());

        return objectNode.toString();
    }
}
