package org.loudsi.simulation.api.algo.neural.network.layer;

import org.loudsi.simulation.api.algo.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;
import org.loudsi.simulation.api.algo.neural.network.neuron.BiasNeuron;
import org.loudsi.simulation.api.algo.neural.network.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends NeuralNetLayer<OutputNeuron> {

    //TODO add sum/cost function ?
    public OutputLayer(String layerName, List<OutputNeuron> neurons, IActivationFunction activationFunction, BiasNeuron bias) {
        super(layerName, neurons, activationFunction, bias);
    }

    @Override
    public void calculateOutput() {
        neurons.forEach(AbstractNeuron::calculateNetInput);
        neurons.forEach(neuron -> neuron.activate(this.activationFunction));
    }

    public double calculateError(ArrayList<Double> desiredValues) {
        if (desiredValues.size() == this.getNeurons().size()) {
            double squareCost = 0;
            for (int i = 0; i < desiredValues.size(); i++) {
                final OutputNeuron outPutNeuron = this.getNeurons().get(i);
                final double singleCost = desiredValues.get(i) - outPutNeuron.getOutput();
                outPutNeuron.setNetError(singleCost);
                outPutNeuron.calculateDeltaError(this.activationFunction);
                squareCost += Math.pow(singleCost, 2);
            }
            return squareCost / 2;
        } else {
            throw new RuntimeException("Invalid output result set");
        }
    }


}
