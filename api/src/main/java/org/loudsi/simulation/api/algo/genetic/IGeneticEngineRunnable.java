package org.loudsi.simulation.api.algo.genetic;

import org.loudsi.simulation.api.runnables.IControlledRunnable;

public interface IGeneticEngineRunnable<Context, GeneticConfig> extends IControlledRunnable {

    GeneticConfig getCopiedConfig();

    Context getCopiedContext();

}
