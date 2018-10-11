package com.keedio.hadoop.collector.bean;

public class MetricValueBean {

    private String metricTimestamp;
    private float metricValue;

    public MetricValueBean() {
        metricTimestamp = "";
        metricValue = 0;
    }

    public MetricValueBean(String metricTimestamp, float metricValue) {
        this.metricTimestamp = metricTimestamp;
        this.metricValue = metricValue;
    }

    public String getMetricTimestamp() {
        return metricTimestamp;
    }

    public void setMetricTimestamp(String metricTimestamp) {
        this.metricTimestamp = metricTimestamp;
    }

    public float getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(float metricValue) {
        this.metricValue = metricValue;
    }

    @Override
    public String toString() {
        return "metricTimestamp:" + metricTimestamp + " metricValue: " + metricValue;
    }
}
