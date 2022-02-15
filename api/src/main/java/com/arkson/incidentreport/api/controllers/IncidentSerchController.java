package com.arkson.incidentreport.api.controllers;

import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IncidentSerchController {

    @PostMapping
    ResponseEntity<List<IncidentVO>> searchByQuery(@RequestBody @Valid IncidentSearchVO incidentSearchVO);

}
