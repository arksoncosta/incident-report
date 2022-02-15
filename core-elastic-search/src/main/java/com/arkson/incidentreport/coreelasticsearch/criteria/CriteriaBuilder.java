package com.arkson.incidentreport.coreelasticsearch.criteria;

import java.util.LinkedList;
import java.util.List;

public abstract class CriteriaBuilder {
    
    protected final List<Object> values = new LinkedList<>();

    protected abstract String criteriaString();

    @Override
    public String toString() {
        return criteriaString();
    }
}
