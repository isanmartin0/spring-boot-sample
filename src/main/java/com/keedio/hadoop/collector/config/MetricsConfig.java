package com.keedio.hadoop.collector.config;

import com.keedio.hadoop.collector.collectors.HostMetricsCollector;
import com.keedio.hadoop.collector.processor.MetricsProcessor;
import com.keedio.hadoop.collector.collectors.YarnServiceMetricsCollector;
import com.keedio.hadoop.collector.service.MetricsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MetricsConfig {

    @Bean
    public MetricsService metricsService() {
        return new MetricsService();
    }

    @Bean
    public HostMetricsCollector hostMetricsCollector() {
        return new HostMetricsCollector();
    }

    @Bean
    public YarnServiceMetricsCollector yarnServiceMetricsCollector() { return new YarnServiceMetricsCollector(); }

    @Bean
    public MetricsProcessor metricsProcessor() { return new MetricsProcessor(); }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
