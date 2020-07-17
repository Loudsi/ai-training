package org.loudsi.simulation.api.algo.neural.network.layer;

import org.loudsi.simulation.api.algo.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;
import org.loudsi.simulation.api.algo.neural.network.neuron.BiasNeuron;

import java.util.List;

public abstract class NeuralNetLayer<T extends AbstractNeuron> {

    protected final String layerName;
    protected final List<T> neurons;
    protected IActivationFunction activationFunction;
    protected BiasNeuron bias;

    public NeuralNetLayer(String layerName, List<T> neurons, IActivationFunction activationFunction, BiasNeuron bias) {
        this.layerName = layerName;
        this.neurons = neurons;
        this.activationFunction = activationFunction;
        this.bias = bias;
    }

    public List<T> getNeurons() {
        return neurons;
    }

    public abstract void calculateOutput();

    public BiasNeuron getBias() {
        return bias;
    }
}
