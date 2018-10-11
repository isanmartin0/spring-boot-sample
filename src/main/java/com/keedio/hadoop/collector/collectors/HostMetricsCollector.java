package com.keedio.hadoop.collector.collectors;

import com.jayway.jsonpath.JsonPath;
import com.keedio.hadoop.collector.MetricsCollectorApp;
import com.keedio.hadoop.collector.bean.MetricValueBean;
import com.keedio.hadoop.collector.bean.MetricsBean;
import com.keedio.hadoop.collector.constants.MetricsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HostMetricsCollector {

    private static Logger logger = LoggerFactory.getLogger(HostMetricsCollector.class);

    @Value("${host.cpu.metric.name}")
    private String hostCPUMetricName;

    @Value("${host.memory.metric.name}")
    private String hostMemoryMetricName;



    public Map<String, MetricsBean> generateHostMetricsMap() {

        logger.info("Executing host metrics collector");

        Map<String, MetricsBean> hostMetricsMap = new LinkedHashMap<>();

        if(logger.isDebugEnabled()) {
            logger.debug("Getting " + hostCPUMetricName + " metric");
        }
        hostMetricsMap.put(hostCPUMetricName, generateHostCPUDummyMetric());
        hostMetricsMap.put(hostMemoryMetricName, generateHostMemoryDummyMetric());

        return hostMetricsMap;
    }

    private MetricsBean generateHostCPUDummyMetric() {
        MetricValueBean mvb = new MetricValueBean();
        List<MetricValueBean> metricValuesList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat(MetricsConstants.TIMESTAMP_FORMAT);

        mvb.setMetricTimestamp(sdf.format(Calendar.getInstance().getTime()));
        mvb.setMetricValue((float) (Math.random()*100));
        metricValuesList.add(mvb);

        MetricsBean mb = new MetricsBean();
        mb.setMetricName(hostCPUMetricName);
        mb.setMetricContext(MetricsConstants.HOST_CONTEXT);
        mb.setMetricUnit(MetricsConstants.VCORES_UNIT);
        mb.setMetricValues(metricValuesList);

        return mb;
    }

    private MetricsBean generateHostMemoryDummyMetric() {
        MetricValueBean mvb = new MetricValueBean();
        List<MetricValueBean> metricValuesList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat(MetricsConstants.TIMESTAMP_FORMAT);

        mvb.setMetricTimestamp(sdf.format(Calendar.getInstance().getTime()));
        mvb.setMetricValue((float) (Math.random()*100));;
        metricValuesList.add(mvb);

        MetricsBean mb = new MetricsBean();
        mb.setMetricName(hostMemoryMetricName);
        mb.setMetricContext(MetricsConstants.HOST_CONTEXT);
        mb.setMetricUnit(MetricsConstants.MB_UNIT);
        mb.setMetricValues(metricValuesList);

        return mb;
    }
}
