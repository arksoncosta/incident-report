package com.arkson.incidentreport.coreelasticsearch.components;

import com.arkson.incidentreport.core.exceptions.NotFoundException;
import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.vo.ElasticSearchResult;
import com.arkson.incidentreport.coreelasticsearch.vo.Hits;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.arkson.incidentreport.coreelasticsearch.utils.HttpUtils.createHttpEntityWithBasicAuth;

@Component
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchQueryImpl implements ElasticSearchQuery {

    @Value("${elasticsearch.search.url}")
    private String elasticSearchUrl;

    @Value("${elasticsearch.username}")
    private String elasticSearchUsername;

    @Value("${elasticsearch.password}")
    private String elasticSearchPassword;

    private final RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public List<Incident> searchByQuery(IncidentSearchVO incidentSearchVO) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var query = ElasticSearchQueryBuilder.generateElasticSearchQuery(incidentSearchVO);

        var entity = createHttpEntityWithBasicAuth(query, elasticSearchUsername, elasticSearchPassword);
        var responseEntity =
                restTemplate.exchange(elasticSearchUrl, HttpMethod.POST, entity, ElasticSearchResult.class);

        stopWatch.stop();
        log.info("Response time es={}ms", stopWatch.getTotalTimeMillis());

        var response = Objects.requireNonNull(responseEntity.getBody(), "ResponseEntity is null");
        return response
                .getHits()
                .getHits()
                .stream()
                .map(Hits::getIncident)
                .collect(Collectors.toList());
    }

    @Override
    public Incident searchById(String id) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var query = ElasticSearchQueryBuilder.buildElasticSearchQueryId(id);

        var entity = createHttpEntityWithBasicAuth(query, elasticSearchUsername, elasticSearchPassword);
        var responseEntity =
                restTemplate.exchange(elasticSearchUrl, HttpMethod.POST, entity, ElasticSearchResult.class);

        stopWatch.stop();
        log.info("Response time es={}ms", stopWatch.getTotalTimeMillis());

        var response = Objects.requireNonNull(responseEntity.getBody(), "ResponseEntity is null");
        return response
                .getHits()
                .getHits()
                .stream()
                .map(Hits::getIncident)
                .findAny().orElseThrow(() -> new NotFoundException("Document not found. Id="+id));
    }
}
