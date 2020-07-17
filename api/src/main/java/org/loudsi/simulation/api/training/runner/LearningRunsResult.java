package org.loudsi.simulation.api.training.runner;

public class LearningRunsResult<K> {

    private K config;
    private double score;

    public LearningRunsResult() {
    }

    public LearningRunsResult(K config, double score) {
        this.config = config;
        this.score = score;
    }

    public K getConfig() {
        return config;
    }

    public void setConfig(K config) {
        this.config = config;
    }

    public double getScore() {
        return score;
    }

}
