package org.loudsi.simulation.api.algo.neural.network;

import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;

public class NeuronsConnection {

    private final AbstractNeuron fromNeuron;
    private final AbstractNeuron toNeuron;

    private double weight;

    public NeuronsConnection(AbstractNeuron fromNeuron, AbstractNeuron toNeuron, double initWeight) {
        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.weight = initWeight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public AbstractNeuron getFromNeuron() {
        return fromNeuron;
    }

    public AbstractNeuron getToNeuron() {
        return toNeuron;
    }
}
