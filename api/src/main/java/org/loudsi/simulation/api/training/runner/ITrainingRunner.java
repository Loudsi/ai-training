package org.loudsi.simulation.api.training.runner;

import org.loudsi.simulation.api.engine.IGeneticManager;

public interface ITrainingRunner<Context, Config> {
    LearningRunsResult<Config> run(Context context, Config config, IGeneticManager<Context, Config> manager);
}
