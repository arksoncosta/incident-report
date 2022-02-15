package com.arkson.incidentreport.coreelasticsearch.service;

import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchUpdateServiceImpl implements ElasticSearchUpdateService {

    @Value("${elasticsearch.update.url}")
    private String elasticSearchUpdateUrl;

    @Value("${elasticsearch.username}")
    private String elasticSearchUsername;

    @Value("${elasticsearch.password}")
    private String elasticSearchPassword;

    private final RestTemplate restTemplate;

    @Override
    public Incident updateElasticSearch(Incident incident) {
        var entity = HttpUtils.createHttpEntityWithBasicAuth(incident, elasticSearchUsername, elasticSearchPassword);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(elasticSearchUpdateUrl)
                .path(incident.getId());

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.PUT, entity, String.class);
        log.info("Item saved successfuly");
        log.info("Update Status={}", responseEntity.getStatusCode());

        return incident;
    }
}
