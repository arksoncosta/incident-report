package com.arkson.incidentreport.coreelasticsearch.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IncidentSearchVO {

    private String title;
    private String description;

    @NotBlank
    private String initialDateTime;

    @NotBlank
    private String finalDateTime;

}
