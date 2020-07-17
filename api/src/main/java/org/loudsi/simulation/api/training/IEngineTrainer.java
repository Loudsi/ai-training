package org.loudsi.simulation.api.training;

import org.loudsi.simulation.api.runnables.IControlledRunnable;

public interface IEngineTrainer<Config, IntermediaryResult> extends IControlledRunnable {
    Config getBestConfig();
    IntermediaryResult getIntermediaryResults();
}
