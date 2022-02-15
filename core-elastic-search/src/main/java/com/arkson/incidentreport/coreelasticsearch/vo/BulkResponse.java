package com.arkson.incidentreport.coreelasticsearch.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BulkResponse {

    private Integer took;
    private boolean errors;
    private List<Item> items;

}
