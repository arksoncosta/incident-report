package com.arkson.incidentreport.cores3.configuration;

import com.arkson.incidentreport.core.configuration.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
@PropertySource(value = "classpath:core-s3.yml", factory = YamlPropertySourceFactory.class)
public class CoreS3Configuration {
}
