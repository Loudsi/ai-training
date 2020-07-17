package org.loudsi.simulation.api.ai.neural.network.layer;

import org.loudsi.simulation.api.ai.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.ai.neural.network.neuron.BiasNeuron;
import org.loudsi.simulation.api.ai.neural.network.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends NeuralNetLayer<InputNeuron> {

    public InputLayer(String layerName, List<InputNeuron> neurons, IActivationFunction activationFunction) {
        super(layerName, neurons, activationFunction, null);
    }

    public void setInputValues(ArrayList<Double> inputValues) {
        if (inputValues.size() == this.getNeurons().size()) {
            for (int i = 0; i < inputValues.size(); i++) {
                this.getNeurons().get(i).setInputValue(inputValues.get(i));
            }
        } else {
            throw new RuntimeException("Invalid input set");
        }
    }

    @Override
    public void calculateOutput() {
        neurons.forEach(neuron -> neuron.activate(this.activationFunction));
    }
}
