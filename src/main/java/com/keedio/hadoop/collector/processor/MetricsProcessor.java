package com.keedio.hadoop.collector.processor;

import com.keedio.hadoop.collector.MetricsCollectorApp;
import com.keedio.hadoop.collector.bean.MetricsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class MetricsProcessor {

    private static Logger logger = LoggerFactory.getLogger(MetricsCollectorApp.class);

    public void processMetricsMap(Map<String, MetricsBean> mapMetrics) {
        logger.info("Executing metrics processor for a map of size " + mapMetrics.size());
        logger.info("Metrics created:");

        for (String mapMetricName : mapMetrics.keySet()) {
            logger.info("Processing map metric: " + mapMetricName);
            logger.info("metric content: ");
            MetricsBean metric = mapMetrics.get(mapMetricName);
            logger.info(metric.toString());
        }
    }
}
