package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.api.mapper.IncidentMapper;
import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.components.ElasticSearchQuery;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class IncidentSerchServiceImpl implements IncidentSerchService {

    private final ElasticSearchQuery elasticSearchQuery;
    private final IncidentMapper incidentMapper;

    @Override
    public List<IncidentVO> searchByQuery(IncidentSearchVO incidentSearchVO) {
        List<Incident> incidents = elasticSearchQuery.searchByQuery(incidentSearchVO);

        return incidents
                .stream()
                .map(this::incidentToIncidentVO)
                .collect(toList());
    }

    private IncidentVO incidentToIncidentVO(Incident incident) {
        return incidentMapper.incidentToIncidentVO(incident);
    }

}
