package org.loudsi.simulation.api.engine;

public interface IGeneticManager<Context, Config> {
    void updateContext(Context context, Config engineConfig, int engineCycle);

    boolean isGoalReached(Context context);

    int getEngineTickInMs(int engineCycle);
}
