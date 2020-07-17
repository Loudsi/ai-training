package org.loudsi.simulation.api.ai.neural.network.neuron;

import org.loudsi.simulation.api.ai.neural.fonction.activation.IActivationFunction;
import org.loudsi.simulation.api.ai.neural.network.NeuronsConnection;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNeuron {

    private final String name;
    protected Double netInput;
    protected Double output;

    protected Double netError;
    protected Double errorDelta;

    private final List<NeuronsConnection> inputConnections = new ArrayList<>();
    private final List<NeuronsConnection> outputConnections = new ArrayList<>();

    public AbstractNeuron(String name) {
        this.name = name;
    }


    public void calculateNetInput() {
        if (this.netInput == null) {
            double weightedSumWithBias = 0;
            final List<NeuronsConnection> inputConnections = this.inputConnections;
            for (NeuronsConnection inputConnection : inputConnections) {
                final AbstractNeuron previousNeuron = inputConnection.getFromNeuron();
                final double connectionWeight = inputConnection.getWeight();
                weightedSumWithBias += previousNeuron.getOutput() * connectionWeight;
                if (Double.isNaN(weightedSumWithBias) || Double.isInfinite(weightedSumWithBias)) {
                    throw new RuntimeException("Not a number net Input");
                }
            }

            this.netInput = weightedSumWithBias;
        }
    }

    public void activate(IActivationFunction activationFunction) {
        if (this.netInput != null) {
            final double output = activationFunction.forward(this.netInput);
            if (Double.isNaN(output) || Double.isInfinite(output)) {
                throw new RuntimeException("Not a number output");
            }
            this.output = output;

        } else {
            throw new RuntimeException("Trying to activate a non feed neuron");
        }
    }

    public void reset() {
        this.netError = null;
        this.errorDelta = null;
        this.netInput = null;
        this.output = null;
    }

    public Double getOutput() {
        return output;
    }

    public void addInputConnection(NeuronsConnection neuronsConnection) {
        this.inputConnections.add(neuronsConnection);
    }

    public List<NeuronsConnection> getInputConnections() {
        return inputConnections;
    }


    public List<NeuronsConnection> getOutputConnections() {
        return outputConnections;
    }

    public void addOutputConnection(NeuronsConnection outputConnection) {
        this.outputConnections.add(outputConnection);
    }

    public Double getErrorDelta() {
        return errorDelta;
    }
}
