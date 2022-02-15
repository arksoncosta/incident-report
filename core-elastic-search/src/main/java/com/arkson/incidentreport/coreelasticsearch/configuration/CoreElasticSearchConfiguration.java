package com.arkson.incidentreport.coreelasticsearch.configuration;

import com.arkson.incidentreport.core.configuration.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
@EnableConfigurationProperties(CoreElasticSearchConfiguration.class)
@ConfigurationProperties(prefix = "elasticsearch")
@PropertySource(value = "classpath:core-elastic-search.yml", factory = YamlPropertySourceFactory.class)
public class CoreElasticSearchConfiguration {
}
