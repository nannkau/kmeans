package edu.sgu.kmeans.dto.kmean;

import java.util.Map;

public class Record {
    private Integer[] label;
    private Map<String,Double> features;

    public Integer[] getLabel() {
        return label;
    }

    public void setLabel(Integer[] label) {
        this.label = label;
    }

    public Map<String, Double> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Double> features) {
        this.features = features;
    }
}

