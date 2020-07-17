package org.loudsi.visual.adapter.engine;

import javafx.scene.canvas.Canvas;
import org.loudsi.simulation.api.engine.IGeneticAlgoRunnable;
import org.loudsi.simulation.api.runnables.RunnableStatus;
import org.loudsi.visual.adapter.IVisualRunnerAdapter;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.jfx.renderer.IRenderer;

import java.util.Map;

public abstract class AbstractEngineAdapter<Context, Config> implements IVisualRunnerAdapter {

    private final IRenderer<Context> renderer;
    private final IGeneticAlgoRunnable<Context, Config> engine;

    public AbstractEngineAdapter(IRenderer<Context> renderer, IGeneticAlgoRunnable<Context, Config> engine) {
        this.renderer = renderer;
        this.engine = engine;
    }


    @Override
    public void start() {
        new Thread(this.engine).start();
    }

    @Override
    public Map<ICanvasName, Canvas> registerCanvas() {
        return renderer.registerCanvas();
    }

    @Override
    public void stop() {
        this.engine.terminate();
    }

    @Override
    public void drawVisuals(Map<ICanvasName, Canvas> canvas) {
        renderer.drawVisuals(this.engine.getCopiedContext(), canvas);
    }

    @Override
    public void pause() {
        this.engine.pause();
    }

    @Override
    public void resume() {
        this.engine.resume();
    }

    @Override
    public RunnableStatus getStatus() {
        return engine.getStatus();
    }
}
