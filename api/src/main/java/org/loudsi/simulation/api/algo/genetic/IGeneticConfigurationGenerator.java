package org.loudsi.simulation.api.algo.genetic;

public interface IGeneticConfigurationGenerator<GeneticConfig> {
    GeneticConfig generate(int randomIntInclusive);

    GeneticConfig mutateFrom(GeneticConfig previousConfig, double variationAmount);
}
