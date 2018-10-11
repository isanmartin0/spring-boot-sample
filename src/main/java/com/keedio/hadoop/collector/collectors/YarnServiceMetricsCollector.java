package com.keedio.hadoop.collector.collectors;

import com.jayway.jsonpath.JsonPath;
import com.keedio.hadoop.collector.bean.MetricValueBean;
import com.keedio.hadoop.collector.bean.MetricsBean;
import com.keedio.hadoop.collector.constants.MetricsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class YarnServiceMetricsCollector {

    private static Logger logger = LoggerFactory.getLogger(YarnServiceMetricsCollector.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat(MetricsConstants.TIMESTAMP_FORMAT);

    @Autowired
    RestTemplate restTemplate;

    @Value("${cloudera.cluster.url}")
    private String clouderaClusterURL;

    @Value("#{'${yarn.service.metrics}'.split(';')}")
    private List<String> yarnMetricsList;

    //Add more properties


    public Map<String, MetricsBean> generateYarnServiceMetricsMap() {

        logger.info("Executing yarn service metrics collector");

        Map<String, MetricsBean> yarnServiceMetricsMap = new LinkedHashMap<>();

        ResponseEntity<String> entity = restTemplate.getForEntity("http://gturnquist-quoters.cfapps.io/api/random", String.class);

        String json = entity.getBody();
        List<String> metrics = JsonPath.read(json, "$.*");
        logger.info(metrics.toString());

        for(String yarnMetricName : yarnMetricsList) {
            MetricsBean yarnMetric = generateYarnDummyMetric(yarnMetricName);
            yarnServiceMetricsMap.put(yarnMetricName, yarnMetric);
        }

        return yarnServiceMetricsMap;
    }


    private MetricsBean generateYarnDummyMetric(String metricName) {
        MetricValueBean mvb = new MetricValueBean();
        List<MetricValueBean> metricValuesList = new ArrayList<>();

        float metricValue = (float) Math.random() * 5;

        mvb.setMetricTimestamp(sdf.format(Calendar.getInstance().getTime()));
        mvb.setMetricValue(metricValue);
        metricValuesList.add(mvb);

        MetricsBean mb = new MetricsBean();
        mb.setMetricName(metricName);
        mb.setMetricContext(MetricsConstants.YARN_SERVICE_CONTEXT);
        if (metricValue > 2) {
            mb.setMetricUnit(MetricsConstants.VCORES_UNIT);
        } else {
            mb.setMetricUnit(MetricsConstants.MB_UNIT);
        }

        mb.setMetricValues(metricValuesList);

        return mb;
    }

}
