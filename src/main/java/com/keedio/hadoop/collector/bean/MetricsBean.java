package com.keedio.hadoop.collector.bean;

import com.keedio.hadoop.collector.constants.MetricsConstants;

import java.util.ArrayList;
import java.util.List;

public class MetricsBean {

    private String metricName;
    private String metricUnit;
    private String metricContext;
    private List<MetricValueBean> metricValues;

    public MetricsBean() {
        metricName = "";
        metricUnit = "";
        metricContext = "";
        metricValues = new ArrayList<>();

    }

    public MetricsBean(String metricName, String metricUnit, String metricContext, List<MetricValueBean> metricValues) {
        this.metricName = metricName;
        this.metricUnit = metricUnit;
        this.metricContext = metricContext;
        this.metricValues = metricValues;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public String getMetricContext() {
        return metricContext;
    }

    public void setMetricContext(String metricContext) {
        this.metricContext = metricContext;
    }

    public List<MetricValueBean> getMetricValues() {
        return metricValues;
    }

    public void setMetricValues(List<MetricValueBean> metricValues) {
        this.metricValues = metricValues;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(MetricsConstants.NEW_LINE);
        sb.append("metricName:").append(metricName).append(MetricsConstants.NEW_LINE);
        sb.append("metricUnit:").append(metricUnit).append(MetricsConstants.NEW_LINE);
        sb.append("metricContext:").append(metricContext).append(MetricsConstants.NEW_LINE);
        sb.append("metricValues:").append(MetricsConstants.NEW_LINE);
        sb.append("[").append(MetricsConstants.NEW_LINE);
        for(MetricValueBean mvb : metricValues) {
            sb.append("\t").append(mvb.toString()).append(MetricsConstants.NEW_LINE);
        }
        sb.append("]").append(MetricsConstants.NEW_LINE);

        return sb.toString();
    }
}
