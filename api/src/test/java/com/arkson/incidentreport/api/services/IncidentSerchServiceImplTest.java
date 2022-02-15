package com.arkson.incidentreport.api.services;

import com.arkson.incidentreport.api.mapper.IncidentMapperImpl;
import com.arkson.incidentreport.api.vo.IncidentVO;
import com.arkson.incidentreport.core.model.Incident;
import com.arkson.incidentreport.coreelasticsearch.components.ElasticSearchQueryImpl;
import com.arkson.incidentreport.coreelasticsearch.vo.IncidentSearchVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.arkson.incidentreport.core.model.Incident.builder;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentSerchServiceImplTest {

    @Mock
    private ElasticSearchQueryImpl elasticSearchQuery;

    @Mock
    private IncidentMapperImpl incidentMapper;

    @InjectMocks
    private IncidentSerchServiceImpl incidentSerchService;

    @BeforeEach
    void setUp() {
        List<Incident> mockIncidents =
                of(builder().id(UUID.randomUUID().toString()).build(),
                        builder().id(UUID.randomUUID().toString()).build());

        when(elasticSearchQuery.searchByQuery(any()))
                .thenReturn(mockIncidents);
    }

    @Test
    void searchByQuery() {
        IncidentSearchVO incidentSearchVO = IncidentSearchVO
                .builder()
                .title("Title search test")
                .build();

        List<IncidentVO> incidents = incidentSerchService.searchByQuery(incidentSearchVO);
        assertNotNull(incidents);
        assertFalse(incidents.isEmpty());

    }
}