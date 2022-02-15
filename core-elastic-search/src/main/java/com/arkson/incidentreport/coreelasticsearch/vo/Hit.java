package com.arkson.incidentreport.coreelasticsearch.vo;

import lombok.Data;

import java.util.List;

@Data
public class Hit {

    private List<Hits> hits;

}
