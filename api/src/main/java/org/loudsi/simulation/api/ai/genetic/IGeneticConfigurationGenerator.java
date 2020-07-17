package org.loudsi.simulation.api.ai.genetic;

public interface IGeneticConfigurationGenerator<K> {
    K generate(int randomIntInclusive);
    K mutateFrom(K previousConfig, double variationAmount);
}
