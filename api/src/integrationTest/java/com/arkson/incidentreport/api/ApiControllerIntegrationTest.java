package com.arkson.incidentreport.api;

import com.arkson.incidentreport.api.vo.IncidentVO;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("integrationTest")
@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerIntegrationTest extends AbstractIntegrationTestApi {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomLocalPort;

    private IncidentVO incidentVO;
    private UriComponentsBuilder uriBuilder;

    @BeforeEach
    void setUp() {
        incidentVO = new IncidentVO();
        incidentVO.setTitle("Incident Title");
        incidentVO.setDescription("Incident Description");

        uriBuilder =
                UriComponentsBuilder
                        .fromUriString("http://localhost")
                        .path("v1/incidents")
                        .port(randomLocalPort);

        super.setup();
    }

    @Test
    void incidentShoudSaveInElasticsearch() {
        super.elasticsearchContainer.start();

        ResponseEntity<IncidentVO> response = restTemplate.postForEntity(uriBuilder.toUriString(), incidentVO, IncidentVO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getHeaders().containsKey("Location"));

        super.elasticsearchContainer.close();
    }

    @Test
    void incidentShoudSaveAndSeachInElasticsearch() {
        super.elasticsearchContainer.start();

        restTemplate.postForEntity(uriBuilder.toUriString(), incidentVO, IncidentVO.class);

        LocalDate currentDate = LocalDate.now();
        String body = String.format("{\"initialDateTime\": \"%s\", \"finalDateTime\": \"%s\"}", currentDate, currentDate);

        HttpEntity<String> httpEntity = createHttpEntityTest(body);
        ResponseEntity<String> result =
                restTemplate.
                    exchange(uriBuilder.toUriString() + "/search", HttpMethod.POST, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        super.elasticsearchContainer.close();
    }

    @NotNull
    private HttpEntity<String> createHttpEntityTest(String body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);
        return httpEntity;
    }
}
