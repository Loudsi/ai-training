package org.loudsi.simulation.implem.ant.config;

import org.loudsi.simulation.implem.ant.context.AntAction;

import java.util.HashMap;

public class BehaviorConfig {

    private double toColonyWeight;
    private double inertiaWeight;
    private double randomness;

    private HashMap<AntAction, Double> antActionWeight;

    public BehaviorConfig() {
    }

    public BehaviorConfig(double toColonyWeight, double inertiaWeight, double randomness, HashMap<AntAction, Double> antActionWeight) {
        this.toColonyWeight = toColonyWeight;
        this.inertiaWeight = inertiaWeight;
        this.randomness = randomness;
        this.antActionWeight = antActionWeight;
    }

    public Double getToColonyWeight() {
        return this.toColonyWeight;
    }

    public Double getInertiaWeight() {
        return this.inertiaWeight;
    }

    public Double getRandomness() {
        return randomness;
    }

    public Double getAntActionWeight(AntAction antAction) {
        return antActionWeight.get(antAction);
    }

    public HashMap<AntAction, Double> getAntActionWeight() {
        return antActionWeight;
    }
}
