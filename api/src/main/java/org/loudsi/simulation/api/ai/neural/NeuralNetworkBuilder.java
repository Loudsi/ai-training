package org.loudsi.simulation.api.ai.neural;

import org.loudsi.simulation.api.ai.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.ai.neural.network.NeuralNetwork;
import org.loudsi.simulation.api.ai.neural.network.layer.HiddenLayer;
import org.loudsi.simulation.api.ai.neural.network.layer.InputLayer;
import org.loudsi.simulation.api.ai.neural.network.layer.OutputLayer;
import org.loudsi.simulation.api.ai.neural.network.neuron.BiasNeuron;
import org.loudsi.simulation.api.ai.neural.network.neuron.HiddenLayerNeuron;
import org.loudsi.simulation.api.ai.neural.network.neuron.InputNeuron;
import org.loudsi.simulation.api.ai.neural.network.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkBuilder {

    //TODO add fluent builder
    public NeuralNetwork buildNeuralNetwork(int inputSize,
                                            int intermediaryLayerNumber,
                                            int numberOfNeuronByLayer,
                                            int outputSize,
                                            IActivationFunction inputFunction,
                                            IActivationFunction hiddenFunction,
                                            IActivationFunction outputFunction) {

        final List<InputNeuron> inputNeurons = new ArrayList<>();
        for (int i = 0; i < inputSize; i++) {
            inputNeurons.add(new InputNeuron("Input :" + i));
        }

        final InputLayer inputNetLayer = new InputLayer("Input Layer", inputNeurons, inputFunction);

        final ArrayList<HiddenLayer> layers = new ArrayList<>();
        for (int i = 0; i < intermediaryLayerNumber; i++) {
            layers.add(this.buildEmptyHiddenLayer("Hidden Layer : " + i, numberOfNeuronByLayer, i, hiddenFunction));
        }

        List<OutputNeuron> outputNeurons = new ArrayList<>();
        for (int i = 0; i < outputSize; i++) {
            outputNeurons.add(new OutputNeuron("Output :" + i));
        }

        final OutputLayer outputNetLayer = new OutputLayer("Output Layer", outputNeurons, outputFunction, new BiasNeuron("Output Bias"));

        return new NeuralNetwork(inputNetLayer, layers, outputNetLayer);
    }

    public HiddenLayer buildEmptyHiddenLayer(String layerName, int numberOfNeuronsByLayers, int intermediaryLayerNumber, IActivationFunction activationFunction) {
        List<HiddenLayerNeuron> hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < numberOfNeuronsByLayers; i++) {
            hiddenNeurons.add(new HiddenLayerNeuron("Hidden :" + intermediaryLayerNumber + ":" + i));
        }
        return new HiddenLayer(layerName, hiddenNeurons, activationFunction, new BiasNeuron("HiddenBias"));
    }

}
