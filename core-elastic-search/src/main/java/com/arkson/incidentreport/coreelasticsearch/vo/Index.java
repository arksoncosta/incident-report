package com.arkson.incidentreport.coreelasticsearch.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Index {

    @JsonProperty("_id")
    private String id;

    private String result;
    private Integer status;

}
