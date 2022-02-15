package com.arkson.incidentreport.coreelasticsearch.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ElasticSearchResult {

    private Long took;

    @JsonProperty("hits")
    private Hit hits;
}
