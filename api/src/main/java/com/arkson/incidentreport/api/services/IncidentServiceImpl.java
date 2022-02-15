package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.core.exceptions.NotFoundException;
import com.arkson.incidentreport.api.mapper.IncidentMapper;
import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.core.transform.IncidentTransform;
import com.arkson.incidentreport.coreelasticsearch.service.ElasticSearchBulkService;
import com.arkson.incidentreport.coreelasticsearch.service.ElasticSearchUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncidentServiceImpl implements IncidentService {

    private final IncidentMapper incidentMapper;
    private final ElasticSearchBulkService elasticSearchBulkService;
    private final ElasticSearchUpdateService elasticSearchUpdateService;

    @Override
    public IncidentVO save(IncidentVO incidentVO) {
        var incident = incidentMapper.incidentVOToIncident(incidentVO);
        IncidentTransform.tranform(incident);

        log.info("Saving incident: {}", incident.getId());
        elasticSearchBulkService.postToElasticSearch(List.of(incident));

        return incidentMapper.incidentToIncidentVO(incident);
    }

    @Override
    public IncidentVO update(IncidentVO incidentVO) {
        log.info("Updating incident={}", incidentVO.getId());
        elasticSearchUpdateService.updateElasticSearch(incidentMapper.incidentVOToIncident(incidentVO));

        return incidentVO;
    }

    @Override
    public IncidentVO findById(String id) {
        throw new NotFoundException("No incident is found");
    }

}

