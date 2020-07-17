package org.loudsi.simulation.api.algo.genetic;

import org.loudsi.simulation.api.training.LearningRunsResult;

public interface IGeneticTrainingRunner<Context, GeneticConfig> {
    LearningRunsResult<GeneticConfig> run(Context context, GeneticConfig geneticConfig, IGeneticManager<Context, GeneticConfig> manager);
}
