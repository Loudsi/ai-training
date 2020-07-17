package org.loudsi.simulation.api.engine;

import org.loudsi.simulation.api.runnables.IControlledRunnable;

public interface IGeneticAlgoRunnable<Context, Config> extends IControlledRunnable {

    Config getCopiedConfig();

    Context getCopiedContext();

}
