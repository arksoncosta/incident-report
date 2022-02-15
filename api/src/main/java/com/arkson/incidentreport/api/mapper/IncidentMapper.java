package com.arkson.incidentreport.api.mapper;

import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.core.model.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    Incident incidentVOToIncident(IncidentVO incidentVO);

    IncidentVO incidentToIncidentVO(Incident incident);

}
