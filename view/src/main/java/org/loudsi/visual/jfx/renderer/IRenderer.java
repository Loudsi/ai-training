package org.loudsi.visual.jfx.renderer;

import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.Map;

public interface IRenderer<T> {
    HashMap<ICanvasName, Canvas> registerCanvas();

    void drawVisuals(T context, Map<ICanvasName, Canvas> canvas);
}
