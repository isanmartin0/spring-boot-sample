package com.keedio.hadoop.collector.service;

import com.keedio.hadoop.collector.bean.MetricsBean;
import com.keedio.hadoop.collector.collectors.HostMetricsCollector;
import com.keedio.hadoop.collector.collectors.YarnServiceMetricsCollector;
import com.keedio.hadoop.collector.processor.MetricsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MetricsService {

    private static Logger logger = LoggerFactory.getLogger(MetricsService.class);

    @Autowired
    private HostMetricsCollector hostMetricsCollector;

    @Autowired
    private YarnServiceMetricsCollector yarnServiceMetricsCollector;

    @Autowired
    private MetricsProcessor metricsProcessor;

    public void generateAndProcessMetricsMap() {

        logger.info("Executing Metrics service");

        Map<String, MetricsBean> mapMetrics = new LinkedHashMap<>();

        //Get host metrics
        Map<String, MetricsBean> mapHostMetrics = hostMetricsCollector.generateHostMetricsMap();

        //Get yarn service metrics
        Map<String, MetricsBean> mapYarnServiceMetrics = yarnServiceMetricsCollector.generateYarnServiceMetricsMap();

        mapMetrics.putAll(mapHostMetrics);
        mapMetrics.putAll(mapYarnServiceMetrics);

        //Process metrics
        metricsProcessor.processMetricsMap(mapMetrics);
    }
}
