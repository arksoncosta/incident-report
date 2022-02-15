package com.arkson.incidentreport.coreelasticsearch.components;

import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;

import java.util.List;

public interface ElasticSearchQuery {

    List<Incident> searchByQuery(IncidentSearchVO incidentSearchVO);

    Incident searchById(String id);

}
