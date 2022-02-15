package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;

import java.util.List;

public interface IncidentSerchService {

    List<IncidentVO> searchByQuery(IncidentSearchVO incidentSearchVO);

}
