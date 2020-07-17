package org.loudsi.simulation.api.ai.neural.network.layer;

import org.loudsi.simulation.api.ai.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.ai.neural.network.neuron.AbstractNeuron;
import org.loudsi.simulation.api.ai.neural.network.neuron.BiasNeuron;

import java.util.List;

public abstract class NeuralNetLayer<T extends AbstractNeuron> {

    protected final String layerName;
    protected IActivationFunction activationFunction;
    protected BiasNeuron bias;
    protected final List<T> neurons;

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
