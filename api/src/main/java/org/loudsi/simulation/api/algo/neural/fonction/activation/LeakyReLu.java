package org.loudsi.simulation.api.algo.neural.fonction.activation;

public class LeakyReLu implements IActivationFunction {

    private final double alpha;

    public LeakyReLu(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public double forward(double value) {
        if (value > 0) {
            return value;
        } else {
            return value * alpha;
        }
    }

    @Override
    public double backward(double value) {
        return value > 0 ? 1 : alpha;
    }
}
