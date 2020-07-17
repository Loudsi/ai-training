package org.loudsi.simulation.api.training.runner.parallele;

import org.loudsi.common.Cloner;
import org.loudsi.simulation.api.algo.genetic.IGeneticEngineRunnable;
import org.loudsi.simulation.api.algo.genetic.IGeneticManager;
import org.loudsi.simulation.api.runnables.BaseRunnable;
import org.loudsi.simulation.api.training.runner.IGeneticTrainingRunner;
import org.loudsi.simulation.api.training.runner.LearningRunsResult;

import java.util.Collection;
import java.util.HashMap;

public abstract class ParallelTrainerRunnerGenetic<Context, GeneticConfig> implements IGeneticTrainingRunner<Context, GeneticConfig> {

    private static final long DEFAULT_RUN_DURATION_MS = 5000;
    private static final long DEFAULT_PARALLEL_RUNS_VALUE = 5;


    @Override
    public LearningRunsResult<GeneticConfig> run(Context context, GeneticConfig geneticConfig, IGeneticManager<Context, GeneticConfig> manager) {

        final HashMap<Thread, IGeneticEngineRunnable<Context, GeneticConfig>> threads = new HashMap<>();

        for (int i = 0; i < this.getNumberOfParallelRuns(); i++) {
            final IGeneticEngineRunnable<Context, GeneticConfig> clonedEngine = this.buildEngine(context, geneticConfig, manager);
            final Thread thread = new Thread(clonedEngine);
            threads.put(thread, clonedEngine);
        }

        threads.keySet().forEach(Thread::start);

        //https://stackoverflow.com/questions/32750386/is-this-the-proper-way-to-time-concurrent-threads-in-java
        try {
            Thread.sleep(this.getAllowedRunTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threads.values().forEach(IGeneticEngineRunnable::terminate);

        return this.buildRunsResult(geneticConfig, threads.values());
    }

    public abstract LearningRunsResult<GeneticConfig> buildRunsResult(GeneticConfig context, Collection<IGeneticEngineRunnable<Context, GeneticConfig>> engines);

    protected IGeneticEngineRunnable<Context, GeneticConfig> buildEngine(Context context, GeneticConfig geneticConfig, IGeneticManager<Context, GeneticConfig> manager) {
        return new BaseRunnable<>(Cloner.deepClone(context), Cloner.deepClone(geneticConfig), manager, true);
    }

    protected long getNumberOfParallelRuns() {
        return DEFAULT_PARALLEL_RUNS_VALUE;
    }

    protected long getAllowedRunTime() {
        return DEFAULT_RUN_DURATION_MS;
    }

}
