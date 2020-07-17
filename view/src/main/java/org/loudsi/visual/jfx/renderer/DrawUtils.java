package org.loudsi.visual.jfx.renderer;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class DrawUtils {

    public static void clearCanvas(Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static Color generateRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    public static Color copyWithOpacity(Color color, double opacity) {
        return Color.color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
    }
}
