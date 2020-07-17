package org.loudsi.simulation.api.algo.neural.network.layer;

import org.loudsi.simulation.api.algo.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;
import org.loudsi.simulation.api.algo.neural.network.neuron.BiasNeuron;
import org.loudsi.simulation.api.algo.neural.network.neuron.HiddenLayerNeuron;

import java.util.List;

public class HiddenLayer extends NeuralNetLayer<HiddenLayerNeuron> {

    public HiddenLayer(String layerName, List<HiddenLayerNeuron> neurons, IActivationFunction activationFunction, BiasNeuron bias) {
        super(layerName, neurons, activationFunction, bias);
    }

    @Override
    public void calculateOutput() {
        neurons.forEach(AbstractNeuron::calculateNetInput);
        neurons.forEach(neuron -> neuron.activate(this.activationFunction));
    }


    public void calculateError() {
        neurons.forEach(HiddenLayerNeuron::calcNetError);
        neurons.forEach(hiddenLayerNeuron -> hiddenLayerNeuron.calculateDeltaError(this.activationFunction));
    }
}
