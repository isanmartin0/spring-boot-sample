package com.keedio.hadoop.collector;

import com.keedio.hadoop.collector.service.MetricsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class MetricsCollectorApp implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(MetricsCollectorApp.class);

    @Autowired
    private MetricsService metricsService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MetricsCollectorApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        logger.info("STARTING THE APPLICATION");
        metricsService.generateAndProcessMetricsMap();
        logger.info("APPLICATION FINISHED");
    }
}
