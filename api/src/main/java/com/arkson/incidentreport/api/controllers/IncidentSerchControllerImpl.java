package com.arkson.incidentreport.api.controllers;

import com.arkson.incidentreport.api.services.IncidentSerchService;
import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/incidents/search")
@RequiredArgsConstructor
@Validated
public class IncidentSerchControllerImpl implements IncidentSerchController {

    private final IncidentSerchService incidentSerchService;

    @Override
    public ResponseEntity<List<IncidentVO>> searchByQuery(IncidentSearchVO incidentSearchVO) {
        return ResponseEntity.ok(incidentSerchService.searchByQuery(incidentSearchVO));
    }
}
