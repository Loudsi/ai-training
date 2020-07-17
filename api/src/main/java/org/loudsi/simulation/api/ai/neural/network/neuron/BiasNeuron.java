package org.loudsi.simulation.api.ai.neural.network.neuron;

public class BiasNeuron extends AbstractNeuron{
    public BiasNeuron(String name) {
        super(name);
    }

    @Override
    public Double getOutput() {
        return 1D;
    }
}
