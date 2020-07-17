package org.loudsi.simulation.api.ai.neural.fonction.activation;

public interface IActivationFunction {
    double forward(double value);
    double backward(double value);
}
