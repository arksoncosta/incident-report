package com.arkson.incidentreport.coreelasticsearch.service;

import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.utils.ElasticSearchBulkUtils;
import com.arkson.incidentreport.coreelasticsearch.utils.HttpUtils;
import com.arkson.incidentreport.coreelasticsearch.vo.BulkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchBulkServiceImpl implements ElasticSearchBulkService {

    @Value("${elasticsearch.url}")
    private String elasticSearchUrl;

    @Value("${elasticsearch.username}")
    private String elasticSearchUsername;

    @Value("${elasticsearch.password}")
    private String elasticSearchPassword;

    private final RestTemplate restTemplate;

    @Retryable(maxAttemptsExpression = "${elasticsearch.retryable.maxAttempts}",
            backoff = @Backoff(delayExpression = "${elasticsearch.retryable.backoffDelay}"))
    @Override
    public BulkResponse postToElasticSearch(List<Incident> incidents) {
        var bulkJson = ElasticSearchBulkUtils.createBulkStringFromList(incidents);

        var entity = HttpUtils.createHttpEntityWithBasicAuth(bulkJson, elasticSearchUsername, elasticSearchPassword);
        var responseEntity =
                restTemplate.exchange(elasticSearchUrl, HttpMethod.POST, entity, BulkResponse.class);
        var bulkResponse = responseEntity.getBody();

        var countSuccess = bulkResponse.getItems().stream()
                .filter(it -> it.getIndex().getStatus() == HttpStatus.CREATED.value())
                .count();
        var countFail = bulkResponse.getItems().stream()
                .filter(it -> it.getIndex().getStatus() != HttpStatus.CREATED.value())
                .count();

        log.info("Items Success={}", countSuccess);
        log.info("Items Fail={}", countFail);

        return bulkResponse;
    }
}
