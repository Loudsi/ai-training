package org.loudsi.simulation.api.algo.neural.network;

import org.loudsi.common.RandomHelper;
import org.loudsi.simulation.api.algo.neural.network.layer.HiddenLayer;
import org.loudsi.simulation.api.algo.neural.network.layer.InputLayer;
import org.loudsi.simulation.api.algo.neural.network.layer.OutputLayer;
import org.loudsi.simulation.api.algo.neural.network.neuron.AbstractNeuron;

public final class LayerConnector {

    private LayerConnector() {
    }

    //TODO ARGHHH switch to generic Asap
    public static void connectLayers(InputLayer current, HiddenLayer next) {
        for (AbstractNeuron currentNeuron : current.getNeurons()) {
            for (AbstractNeuron nextNetLayerNeuron : next.getNeurons()) {
                //Bias connection
                final NeuronsConnection biasConnection = new NeuronsConnection(next.getBias(), nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                nextNetLayerNeuron.addInputConnection(biasConnection);
                //Previous Layer connection
                final NeuronsConnection neuronsConnection = new NeuronsConnection(currentNeuron, nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                currentNeuron.addOutputConnection(neuronsConnection);
                nextNetLayerNeuron.addInputConnection(neuronsConnection);
            }
        }
    }

    public static void connectLayers(HiddenLayer current, HiddenLayer next) {
        for (AbstractNeuron currentNeuron : current.getNeurons()) {
            for (AbstractNeuron nextNetLayerNeuron : next.getNeurons()) {
                //Bias connection
                final NeuronsConnection biasConnection = new NeuronsConnection(next.getBias(), nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                nextNetLayerNeuron.addInputConnection(biasConnection);
                //Previous Layer connection
                final NeuronsConnection neuronsConnection = new NeuronsConnection(currentNeuron, nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                currentNeuron.addOutputConnection(neuronsConnection);
                nextNetLayerNeuron.addInputConnection(neuronsConnection);
            }
        }
    }

    public static void connectLayers(HiddenLayer current, OutputLayer next) {
        for (AbstractNeuron currentNeuron : current.getNeurons()) {
            for (AbstractNeuron nextNetLayerNeuron : next.getNeurons()) {
                //Bias connection
                final NeuronsConnection biasConnection = new NeuronsConnection(next.getBias(), nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                nextNetLayerNeuron.addInputConnection(biasConnection);
                //Previous Layer connection
                final NeuronsConnection neuronsConnection = new NeuronsConnection(currentNeuron, nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                currentNeuron.addOutputConnection(neuronsConnection);
                nextNetLayerNeuron.addInputConnection(neuronsConnection);
            }
        }
    }

    public static void connectLayers(InputLayer current, OutputLayer next) {
        for (AbstractNeuron currentNeuron : current.getNeurons()) {
            for (AbstractNeuron nextNetLayerNeuron : next.getNeurons()) {
                //Bias connection
                final NeuronsConnection biasConnection = new NeuronsConnection(next.getBias(), nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                nextNetLayerNeuron.addInputConnection(biasConnection);
                //Previous Layer connection
                final NeuronsConnection neuronsConnection = new NeuronsConnection(currentNeuron, nextNetLayerNeuron, RandomHelper.randomDoubleInclusive(0, 1));
                currentNeuron.addOutputConnection(neuronsConnection);
                nextNetLayerNeuron.addInputConnection(neuronsConnection);
            }
        }
    }
}
