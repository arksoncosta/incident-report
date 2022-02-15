package com.arkson.incidentreport.coreelasticsearch.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public final class HttpUtils {

    public static HttpEntity<?> createHttpEntityWithBasicAuth(Object data, String elasticSearchUsername, String elasticSearchPassword) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION,
                "Basic " + HttpHeaders.encodeBasicAuth(elasticSearchUsername, elasticSearchPassword, Charset.defaultCharset()));
        return new HttpEntity<>(data, headers);
    }

}
