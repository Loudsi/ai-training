package org.loudsi.simulation.api.training;

import org.loudsi.simulation.api.runnables.IControlledRunnable;

public interface ITrainerRunnable<TrainingResult, IntermediaryResult> extends IControlledRunnable {
    TrainingResult getBestTrainingResult();

    IntermediaryResult getIntermediaryResults();
}
