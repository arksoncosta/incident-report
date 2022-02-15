package com.arkson.incidentreport.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class IncidentVO {

    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;
    private String objectUrl;
    private LocalDateTime createdDate;

}
