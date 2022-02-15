package com.arkson.incidentreport.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GatewayGlobalFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            log.info("Request filter URI={}, Path={}", exchange.getRequest().getURI(), exchange.getRequest().getPath());

            return chain.filter(exchange);
        };
    }
}
