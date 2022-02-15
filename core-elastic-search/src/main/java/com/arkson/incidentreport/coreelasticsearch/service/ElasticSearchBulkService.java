package com.arkson.incidentreport.coreelasticsearch.service;

import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.vo.BulkResponse;

import java.util.List;

public interface ElasticSearchBulkService {

    BulkResponse postToElasticSearch(List<Incident> incidents);

}
