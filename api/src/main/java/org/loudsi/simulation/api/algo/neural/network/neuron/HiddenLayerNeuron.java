package org.loudsi.simulation.api.algo.neural.network.neuron;

import org.loudsi.simulation.api.algo.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.algo.neural.network.NeuronsConnection;

public class HiddenLayerNeuron extends AbstractNeuron {

    public HiddenLayerNeuron(String name) {
        super(name);
    }

    public void calcNetError() {
        double weightedErrorSum = 0D;
        for (NeuronsConnection outputConnection : this.getOutputConnections()) {
            AbstractNeuron rightNeuron = outputConnection.getToNeuron();
            weightedErrorSum += rightNeuron.getErrorDelta() * outputConnection.getWeight();
        }
        if (Double.isNaN(weightedErrorSum)) {
            throw new RuntimeException("net Error is Nan");
        }
        this.netError = weightedErrorSum;
    }

    public void calculateDeltaError(IActivationFunction activationFunction) {
        final double errorDelta = activationFunction.backward(this.netInput) * netError;
        if (Double.isNaN(errorDelta)) {
            throw new RuntimeException("Error delta is Nan");
        }
        this.errorDelta = errorDelta;
    }
}
