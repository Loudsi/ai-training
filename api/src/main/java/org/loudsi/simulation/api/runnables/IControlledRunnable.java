package org.loudsi.simulation.api.runnables;

public interface IControlledRunnable extends Runnable {

    int getCycle();

    void pause();

    void resume();

    void terminate();

    RunnableStatus getStatus();

    boolean isGoalReached();
}
