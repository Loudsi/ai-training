package org.loudsi.simulation.api.training.runner.parallele;

import org.loudsi.common.Cloner;
import org.loudsi.simulation.api.engine.BaseRunnable;
import org.loudsi.simulation.api.engine.IGeneticAlgoRunnable;
import org.loudsi.simulation.api.engine.IGeneticManager;
import org.loudsi.simulation.api.training.runner.ITrainingRunner;
import org.loudsi.simulation.api.training.runner.LearningRunsResult;

import java.util.Collection;
import java.util.HashMap;

public abstract class ParallelTrainerRunner<Context, Config> implements ITrainingRunner<Context, Config> {

    private static final long DEFAULT_RUN_DURATION_MS = 5000;
    private static final long DEFAULT_PARALLEL_RUNS_VALUE = 5;


    @Override
    public LearningRunsResult<Config> run(Context context, Config config, IGeneticManager<Context, Config> manager) {

        final HashMap<Thread, IGeneticAlgoRunnable<Context, Config>> threads = new HashMap<>();

        for (int i = 0; i < this.getNumberOfParallelRuns(); i++) {
            final IGeneticAlgoRunnable<Context, Config> clonedEngine = this.buildEngine(context, config, manager);
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

        threads.values().forEach(IGeneticAlgoRunnable::terminate);

        return this.buildRunsResult(config, threads.values());
    }

    public abstract LearningRunsResult<Config> buildRunsResult(Config context, Collection<IGeneticAlgoRunnable<Context, Config>> engines);

    protected IGeneticAlgoRunnable<Context, Config> buildEngine(Context context, Config config, IGeneticManager<Context, Config> manager) {
        return new BaseRunnable<>(Cloner.deepClone(context), Cloner.deepClone(config), manager, true);
    }

    protected long getNumberOfParallelRuns() {
        return DEFAULT_PARALLEL_RUNS_VALUE;
    }

    protected long getAllowedRunTime() {
        return DEFAULT_RUN_DURATION_MS;
    }

}
