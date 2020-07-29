package org.loudsi.simulation.api.algo.neural.network;

import org.loudsi.simulation.api.algo.neural.network.layer.HiddenLayer;
import org.loudsi.simulation.api.algo.neural.network.layer.InputLayer;
import org.loudsi.simulation.api.algo.neural.network.layer.OutputLayer;
import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private final ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>();
    private final InputLayer inputLayer;
    private final OutputLayer outputLayer;


    public NeuralNetwork(InputLayer inputLayer,
                         List<HiddenLayer> hiddenLayers,
                         OutputLayer outputLayer) {
        this.inputLayer = inputLayer;
        this.hiddenLayers.addAll(hiddenLayers);
        this.outputLayer = outputLayer;
        this.connectNetwork();
    }


    public OutputLayer calculateNeuralNetworkOutput() {
        inputLayer.calculateOutput();
        hiddenLayers.forEach(HiddenLayer::calculateOutput);
        outputLayer.calculateOutput();
        return outputLayer;
    }

    public void setInputValues(ArrayList<Double> inputValues) {
        inputLayer.setInputValues(inputValues);
    }


    public void connectNetwork() {
        if (hiddenLayers.size() > 0) {
            LayerConnector.connectLayers(inputLayer, hiddenLayers.get(0));
            for (int i = 0; i < hiddenLayers.size() - 1; i++) {
                final HiddenLayer neuralNetLayer = this.hiddenLayers.get(i);
                final HiddenLayer nextNetLayer = this.hiddenLayers.get(i + 1);
                LayerConnector.connectLayers(neuralNetLayer, nextNetLayer);
            }
            LayerConnector.connectLayers(hiddenLayers.get(hiddenLayers.size() - 1), outputLayer);
        } else {
            LayerConnector.connectLayers(inputLayer, outputLayer);
        }
    }


    public void resetNeurons() {
        this.inputLayer.getNeurons().forEach(AbstractNeuron::reset);
        this.hiddenLayers.forEach(layer -> layer.getNeurons().forEach(AbstractNeuron::reset));
        this.outputLayer.getNeurons().forEach(AbstractNeuron::reset);
    }

    //http://helios.mi.parisdescartes.fr/~bouzy/Doc/AA1/ReseauxDeNeurones1.pdf
    public Double propagateError(ArrayList<Double> desiredValues) {
        final double totalCost = outputLayer.calculateError(desiredValues);
        for (int i = hiddenLayers.size() - 1; i >= 0; i--) {
            hiddenLayers.get(i).calculateError();
        }
        return totalCost;
    }

    public void updateWeights(double learningRate) {
        outputLayer.getNeurons().forEach(outputNeuron -> updateInputWeights(learningRate, outputNeuron));
        hiddenLayers.forEach(hiddenLayer -> hiddenLayer.getNeurons().forEach(hiddenLayerNeuron -> {
            updateInputWeights(learningRate, hiddenLayerNeuron);
        }));
    }

    private void updateInputWeights(double learningRate, AbstractNeuron neuron) {
        neuron.getInputConnections().forEach(neuronsConnection -> {
            final AbstractNeuron leftNeuron = neuronsConnection.getFromNeuron();
            final AbstractNeuron rightNeuron = neuronsConnection.getToNeuron();
            double deltaWeight = learningRate * rightNeuron.getErrorDelta() * leftNeuron.getOutput();
            final double newWeight = neuronsConnection.getWeight() + deltaWeight;

            if (Double.isNaN(newWeight) || Double.isInfinite(newWeight)) {
                throw new RuntimeException("Not a number new weight");
            }
            neuronsConnection.setWeight(newWeight);
        });
    }
}
