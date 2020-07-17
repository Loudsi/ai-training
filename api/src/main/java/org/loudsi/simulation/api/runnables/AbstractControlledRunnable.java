package org.loudsi.simulation.api.runnables;


public abstract class AbstractControlledRunnable implements IControlledRunnable {

    private RunnableStatus engineStatus;
    private int cycle;

    public AbstractControlledRunnable() {
        this.engineStatus = RunnableStatus.PENDING_START;
        this.cycle = 1;
    }

    @Override
    public void run() {
        this.engineStatus = RunnableStatus.RUNNING;
        this.beforeLoop();
        try {
            while (!getStatus().equals(RunnableStatus.PENDING_STOP) && !isGoalReached()) {

                if (getStatus().equals(RunnableStatus.PENDING_PAUSED)) {
                    this.engineStatus = RunnableStatus.PAUSED;
                }
                if (getStatus().equals(RunnableStatus.PENDING_RESUME)) {
                    this.engineStatus = RunnableStatus.RUNNING;
                }

                if (getStatus().equals(RunnableStatus.PAUSED)) {
                    //TODO improve this to have non blocking operation
                    Thread.sleep(500);
                } else {
                    this.doRunnableLogic(this.cycle);
                    this.cycle++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.engineStatus = RunnableStatus.STOPPED;
    }

    protected void beforeLoop() {
        //DO nothing by default};
    }

    protected abstract void doRunnableLogic(int cycle) throws InterruptedException;

    public abstract boolean isGoalReached();


    @Override
    public RunnableStatus getStatus() {
        return engineStatus;
    }

    @Override
    public synchronized void terminate() {
        this.engineStatus = RunnableStatus.PENDING_STOP;
    }

    @Override
    public void pause() {
        this.engineStatus = RunnableStatus.PENDING_PAUSED;
    }

    @Override
    public void resume() {
        this.engineStatus = RunnableStatus.PENDING_RESUME;
    }

    @Override
    public int getCycle() {
        return cycle;
    }

}

