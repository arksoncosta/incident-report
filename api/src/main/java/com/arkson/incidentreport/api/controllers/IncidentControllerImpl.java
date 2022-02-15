package com.arkson.incidentreport.api.controllers;

import com.arkson.incidentreport.api.services.IncidentService;
import com.arkson.incidentreport.api.vo.IncidentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/incidents")
@RequiredArgsConstructor
@Validated
public class IncidentControllerImpl implements IncidentController {

    private final IncidentService incidentService;

    @Override
    public ResponseEntity<IncidentVO> save(IncidentVO incident, UriComponentsBuilder uriComponentsBuilder) {
        var incidentSaved = incidentService.save(incident);

        var uri = uriComponentsBuilder.path("/v1/incidents/{id}").buildAndExpand(incidentSaved.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<IncidentVO> update(IncidentVO incidentVO, UriComponentsBuilder uriComponentsBuilder) {
        var incidentSaved = incidentService.update(incidentVO);

        var uri = uriComponentsBuilder.path("/v1/incidents/{id}").buildAndExpand(incidentSaved.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<IncidentVO> findById(String id) {
        return ResponseEntity.ok(incidentService.findById(id));
    }

}
