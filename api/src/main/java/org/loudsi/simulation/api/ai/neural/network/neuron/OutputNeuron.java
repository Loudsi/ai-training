package org.loudsi.simulation.api.ai.neural.network.neuron;

import org.loudsi.simulation.api.ai.neural.fonction.activation.IActivationFunction;

public class OutputNeuron extends AbstractNeuron {

    public OutputNeuron(String name) {
        super(name);
    }

    public void setNetError(double netError){
        this.netError = netError;
    }

    public void calculateDeltaError(IActivationFunction activationFunction) {
        this.errorDelta = activationFunction.backward(this.netInput) * this.netError;
    }
}
