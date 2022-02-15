package com.arkson.incidentreport.coreelasticsearch.vo;

import com.arkson.incidentreport.core.model.Incident;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hits {

    @JsonProperty("_index")
    private String index;

    @JsonProperty("_type")
    private String type;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_score")
    private double score;

    @JsonProperty("_source")
    private Incident incident;

}
