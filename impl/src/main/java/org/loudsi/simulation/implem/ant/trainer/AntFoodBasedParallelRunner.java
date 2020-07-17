package org.loudsi.simulation.implem.ant.trainer;

import org.loudsi.simulation.api.engine.IGeneticAlgoRunnable;
import org.loudsi.simulation.api.training.runner.LearningRunsResult;
import org.loudsi.simulation.api.training.runner.parallele.ParallelTrainerRunner;
import org.loudsi.simulation.implem.ant.config.AntEngineConfig;
import org.loudsi.simulation.implem.ant.context.AntColony;
import org.loudsi.simulation.implem.ant.context.AntContext;

import java.util.Collection;

public class AntFoodBasedParallelRunner extends ParallelTrainerRunner<AntContext, AntEngineConfig> {

    @Override
    protected long getAllowedRunTime() {
        return 2000;
    }

    @Override
    public LearningRunsResult<AntEngineConfig> buildRunsResult(AntEngineConfig config, Collection<IGeneticAlgoRunnable<AntContext, AntEngineConfig>> engines) {
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
                .mapToDouble(IGeneticAlgoRunnable::getCycle)
                .sum();

        final double score = (foodCount * foodCount) / totalCycle * 100;
        return new LearningRunsResult<>(config, score);
    }
};

