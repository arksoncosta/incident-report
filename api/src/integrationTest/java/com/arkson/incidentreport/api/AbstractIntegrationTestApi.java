package com.arkson.incidentreport.api;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AbstractIntegrationTestApi {

    protected static final int ELASTIC_SEARCH_PORT = 9200;

    protected ElasticsearchContainer elasticsearchContainer;

    protected void setup() {
        Map<String, String> envs = new HashMap<>();
        envs.put("discovery.type", "single-node");
        envs.put("ES_JAVA_OPTS", "-Xms512m -Xmx512m");

        Consumer<CreateContainerCmd> cmd = e ->
                e.getHostConfig()
                        .withPortBindings(new PortBinding(Ports.Binding.bindPort(ELASTIC_SEARCH_PORT),
                                new ExposedPort(ELASTIC_SEARCH_PORT)));

        elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.14.0")
                .withEnv(envs)
                .withExposedPorts(ELASTIC_SEARCH_PORT)
                .withCreateContainerCmdModifier(cmd);
    }

}
