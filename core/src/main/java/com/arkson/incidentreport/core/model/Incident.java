package com.arkson.incidentreport.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {

    private String id;
    private String title;
    private String description;
    private String objectUrl;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSS")
    private LocalDateTime createdDate;

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
