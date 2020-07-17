package org.loudsi.simulation.api.algo.genetic;

public interface IGeneticManager<Context, GeneticConfig> {

    void updateContext(Context context, GeneticConfig engineGeneticConfig, int engineCycle);

    boolean isGoalReached(Context context);

    int getEngineTickInMs(int engineCycle);

}
