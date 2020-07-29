package org.loudsi.simulation.api.runnables;


import org.loudsi.common.Cloner;
import org.loudsi.simulation.api.algo.genetic.IGeneticEngineRunnable;
import org.loudsi.simulation.api.algo.genetic.IGeneticManager;

import java.util.Objects;

public class BaseRunnable<T, K> extends AbstractControlledRunnable implements IGeneticEngineRunnable<T, K> {

    private final IGeneticManager<T, K> engineManager;
    private final K engineConfig;
    private final T context;

    final boolean isTrainingMode;

    private K copiedEngineConfig;
    private T copiedContext;


    public BaseRunnable(T initialContext, K config, IGeneticManager<T, K> engineManager) {
        this(initialContext, config, engineManager, false);
    }

    public BaseRunnable(T initialContext, K config, IGeneticManager<T, K> engineManager, boolean isTrainingMode) {

        this.isTrainingMode = isTrainingMode;
        this.engineManager = engineManager;

        this.context = Cloner.deepClone(Objects.requireNonNull(initialContext));
        this.engineConfig = Objects.requireNonNull(config);

        this.copiedContext = Cloner.deepClone(this.context);
        this.copiedEngineConfig = Cloner.deepClone(this.engineConfig);
    }


    @Override
    protected void doRunnableLogic(int cycle) throws InterruptedException {
        if (this.isTrainingMode) {
            this.copiedContext = this.context;
            this.copiedEngineConfig = this.engineConfig;
        } else {
            Thread.sleep(engineManager.getEngineTickInMs(cycle));
            this.copiedContext = Cloner.deepClone(this.context);
            this.copiedEngineConfig = Cloner.deepClone(this.engineConfig);
        }
        engineManager.updateContext(this.context, this.engineConfig, cycle);
    }

    @Override
    public boolean isGoalReached() {
        return engineManager.isGoalReached(context);
    }

    @Override
    public synchronized T getCopiedContext() {
        return copiedContext;
    }

    @Override
    public synchronized K getCopiedConfig() {
        return copiedEngineConfig;
    }


}

