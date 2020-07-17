package org.loudsi.simulation.api.algo.neural.fonction.activation;

public class ReLu implements IActivationFunction {
    @Override
    public double forward(double value) {
        return Math.max(0, value);
    }

    @Override
    public double backward(double value) {
        return value > 0 ? 1 : 0;
    }
}
