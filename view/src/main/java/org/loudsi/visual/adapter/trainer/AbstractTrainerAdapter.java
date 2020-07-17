package org.loudsi.visual.adapter.trainer;

import javafx.scene.canvas.Canvas;
import org.loudsi.simulation.api.runnables.RunnableStatus;
import org.loudsi.simulation.api.training.ITrainerRunnable;
import org.loudsi.visual.adapter.IVisualRunnerAdapter;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.jfx.renderer.IRenderer;

import java.util.Map;

public abstract class AbstractTrainerAdapter<Config, IntermediaryResult> implements IVisualRunnerAdapter {

    private final ITrainerRunnable<Config, IntermediaryResult> engineTrainer;
    private final IRenderer<IntermediaryResult> intermediaryResultRenderer;

    protected AbstractTrainerAdapter(ITrainerRunnable<Config, IntermediaryResult> engineTrainer, IRenderer<IntermediaryResult> intermediaryResultRenderer) {
        this.engineTrainer = engineTrainer;
        this.intermediaryResultRenderer = intermediaryResultRenderer;
    }

    @Override
    public void drawVisuals(Map<ICanvasName, Canvas> registeredCanvas) {
        intermediaryResultRenderer.drawVisuals(engineTrainer.getIntermediaryResults(), registeredCanvas);
    }

    @Override
    public void stop() {
        engineTrainer.terminate();
    }

    @Override
    public Map<ICanvasName, Canvas> registerCanvas() {
        return intermediaryResultRenderer.registerCanvas();
    }

    @Override
    public void start() {
        Thread thread = new Thread(engineTrainer);
        thread.start();
    }

    @Override
    public void pause() {
        engineTrainer.pause();
    }

    @Override
    public void resume() {
        engineTrainer.resume();
    }

    @Override
    public RunnableStatus getStatus() {
        return engineTrainer.getStatus();
    }
}
