package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.api.vo.IncidentVO;

public interface IncidentService {

    IncidentVO save(IncidentVO incidentVO);

    IncidentVO update(IncidentVO incidentVO);

    IncidentVO findById(String id);

}
