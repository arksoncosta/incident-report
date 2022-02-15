package com.arkson.incidentreport.core.transform;

import com.arkson.incidentreport.core.model.Incident;

import java.time.LocalDateTime;
import java.util.UUID;

public final class IncidentTransform {

    public static void tranform(Incident incident) {
        if (incident.isNew()) {
            incident.setCreatedDate(LocalDateTime.now());
            incident.setId(UUID.randomUUID().toString());
        }
    }

}
