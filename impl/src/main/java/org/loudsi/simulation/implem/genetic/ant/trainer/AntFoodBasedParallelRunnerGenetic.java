package org.loudsi.simulation.implem.genetic.ant.trainer;

import org.loudsi.simulation.api.algo.genetic.IGeneticEngineRunnable;
import org.loudsi.simulation.api.training.LearningRunsResult;
import org.loudsi.simulation.api.algo.genetic.ParallelTrainerRunnerGenetic;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.simulation.implem.genetic.ant.context.AntColony;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;

import java.util.Collection;

public class AntFoodBasedParallelRunnerGenetic extends ParallelTrainerRunnerGenetic<AntContext, AntEngineGeneticConfig> {

    @Override
    protected long getAllowedRunTime() {
        return 2000;
    }

    @Override
    public LearningRunsResult<AntEngineGeneticConfig> buildRunsResult(AntEngineGeneticConfig config, Collection<IGeneticEngineRunnable<AntContext, AntEngineGeneticConfig>> engines) {
        final double foodCount = engines
                .stream()
                .mapToInt(
                        engine -> engine.getCopiedContext()
                                .getColonies()
                                .stream()
                                .mapToInt(AntColony::getColonyFood)
                                .sum())
                .sum();

        final double totalCycle = engines
                .stream()
                .mapToDouble(IGeneticEngineRunnable::getCycle)
                .sum();

        final double score = (foodCount * foodCount) / totalCycle * 100;
        return new LearningRunsResult<>(config, score);
    }
};

