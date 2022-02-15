package com.arkson.incidentreport.api.controllers;

import com.arkson.incidentreport.api.vo.IncidentVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

public interface IncidentController {

    @PostMapping
    ResponseEntity<IncidentVO> save(@RequestBody @Valid IncidentVO incident, UriComponentsBuilder uriComponentsBuilder);

    @PutMapping
    ResponseEntity<IncidentVO> update(@RequestBody @Valid IncidentVO incidentVO, UriComponentsBuilder uriComponentsBuilder);

    @GetMapping("{id}")
    ResponseEntity<IncidentVO> findById(@PathVariable String id);

}
