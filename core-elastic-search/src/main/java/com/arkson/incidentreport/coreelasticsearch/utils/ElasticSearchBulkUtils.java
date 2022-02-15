package com.arkson.incidentreport.coreelasticsearch.utils;

import com.arkson.incidentreport.core.model.Incident;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;


public final class ElasticSearchBulkUtils {

    public static String createBulkStringFromList(List<Incident> incidents) {
        return incidents.stream()
                .map(i -> String.format("%s\n%s", String.format("{\"index\": {\"_id\": \"%s\"}}", i.getId()), toJson(i)))
                .collect(Collectors.joining("\n")) + "\n";
    }

    @SneakyThrows
    private static String toJson(Incident incident) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(incident);
    }
}
