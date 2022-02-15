package com.arkson.incidentreport.coreelasticsearch.service;

import com.arkson.incidentreport.core.model.Incident;

public interface ElasticSearchUpdateService {

    Incident updateElasticSearch(Incident incident);

}
