package org.loudsi.simulation.implem.ant.config;

import org.loudsi.simulation.implem.ant.context.AntAction;

import java.util.HashMap;


public class AntEngineConfig {

    private HashMap<AntAction, BehaviorConfig> antBehaviorConfig;
    private HashMap<AntAction, Double> antScentDetectionRadiusConfig;
    private HashMap<AntAction, Integer> antPheromoneDepositFrequencyConfig;
    private HashMap<AntAction, Integer> antPheromoneStrenghtConfig;

    public AntEngineConfig() {
    }

    public AntEngineConfig(HashMap<AntAction, BehaviorConfig> antBehaviorConfig, HashMap<AntAction, Double> antScentDetectionRadiusConfig, HashMap<AntAction, Integer> antPheromoneDepositFrequencyConfig, HashMap<AntAction, Integer> antPheromoneStrenghtConfig) {
        this.antBehaviorConfig = antBehaviorConfig;
        this.antScentDetectionRadiusConfig = antScentDetectionRadiusConfig;
        this.antPheromoneDepositFrequencyConfig = antPheromoneDepositFrequencyConfig;
        this.antPheromoneStrenghtConfig = antPheromoneStrenghtConfig;
    }

    public HashMap<AntAction, BehaviorConfig> getAntBehaviorConfig() {
        return antBehaviorConfig;
    }

    public HashMap<AntAction, Double> getAntScentDetectionRadiusConfig() {
        return antScentDetectionRadiusConfig;
    }

    public HashMap<AntAction, Integer> getAntPheromoneDepositFrequencyConfig() {
        return antPheromoneDepositFrequencyConfig;
    }

    public HashMap<AntAction, Integer> getAntPheromoneStrenghtConfig() {
        return antPheromoneStrenghtConfig;
    }
}
