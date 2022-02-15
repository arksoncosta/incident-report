package com.arkson.incidentreport.coreelasticsearch.criteria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CriteriaBuilderId extends CriteriaBuilder {

    public CriteriaBuilderId(String id) {
        super.values.add(id);
    }

    @Override
    protected String criteriaString() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("term")
                .put("_id", super.values.get(0).toString());

        return objectNode.toString();
    }
}
