package org.loudsi.visual.adapter;

import javafx.scene.canvas.Canvas;
import org.loudsi.simulation.api.runnables.RunnableStatus;
import org.loudsi.visual.jfx.renderer.ICanvasName;

import java.util.Map;

public interface IVisualRunnerAdapter {

    void drawVisuals(Map<ICanvasName, Canvas> registeredCanvas);

    void stop();

    Map<ICanvasName, Canvas> registerCanvas();

    void start();

    void pause();

    void resume();

    RunnableStatus getStatus();
}
