package org.loudsi.simulation.api.ai.neural.fonction.activation;

public class Sigmoid implements IActivationFunction{

    @Override
    public double forward(double value) {
        return 1.0 / (1.0 + Math.exp(-1.0 * value));
    }

    @Override
    public double backward(double value) {
        return this.forward(value) * (1.0 - this.forward(value));
    }
}
