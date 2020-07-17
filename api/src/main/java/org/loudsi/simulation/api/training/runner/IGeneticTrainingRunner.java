package org.loudsi.simulation.api.training.runner;

import org.loudsi.simulation.api.algo.genetic.IGeneticManager;

public interface IGeneticTrainingRunner<Context, Config> {
    LearningRunsResult<Config> run(Context context, Config config, IGeneticManager<Context, Config> manager);
}
