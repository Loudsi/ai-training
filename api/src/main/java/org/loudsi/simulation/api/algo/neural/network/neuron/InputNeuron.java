package org.loudsi.simulation.api.algo.neural.network.neuron;

public class InputNeuron extends AbstractNeuron {

    public InputNeuron(String name) {
        super(name);
    }

    public void setInputValue(Double inputValue) {
        if (inputValue != null) {
            this.netInput = inputValue;
        } else {
            throw new RuntimeException();
        }
    }
}
