package org.acme.demo20220330;

import java.util.List;

public class Result {
    private Boolean predictions;
    private List<Number> scores;
    private List<String> names;
    public Boolean getPredictions() {
        return predictions;
    }
    public void setPredictions(Boolean predictions) {
        this.predictions = predictions;
    }
    public List<Number> getScores() {
        return scores;
    }
    public void setScores(List<Number> scores) {
        this.scores = scores;
    }
    public List<String> getNames() {
        return names;
    }
    public void setNames(List<String> names) {
        this.names = names;
    }

    
}
