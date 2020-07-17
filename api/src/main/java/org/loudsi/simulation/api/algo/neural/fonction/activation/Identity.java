package org.loudsi.simulation.api.algo.neural.fonction.activation;

public class Identity implements IActivationFunction {
    @Override
    public double forward(double value) {
        return value;
    }

    @Override
    public double backward(double value) {
        return 1;
    }
}
