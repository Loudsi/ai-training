package org.loudsi.simulation.api.ai.neural.network;

import org.loudsi.simulation.api.ai.neural.network.neuron.AbstractNeuron;

public class NeuronsConnection {

    private final AbstractNeuron fromNeuron;
    private final AbstractNeuron toNeuron;
    private double weight;

    public NeuronsConnection(AbstractNeuron fromNeuron, AbstractNeuron toNeuron, double weight) {
        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.weight = weight;
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
