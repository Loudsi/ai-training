package org.loudsi.simulation.api.training.runner;

public class LearningRun<T> {

    private T runResult;
    private long elapsed;
    private long cycle;

    public LearningRun() {
    }

    public LearningRun(long elapsed, long cycle, T runResult) {
        this.elapsed = elapsed;
        this.cycle = cycle;
        this.runResult = runResult;
    }

    public T getRunResult() {
        return this.runResult;
    }

    public long getElapsed() {
        return elapsed;
    }

    public long getCycle() {
        return cycle;
    }
}
