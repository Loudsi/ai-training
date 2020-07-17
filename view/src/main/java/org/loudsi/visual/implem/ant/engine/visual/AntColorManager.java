package org.loudsi.visual.implem.ant.engine.visual;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.loudsi.simulation.implem.ant.context.AntColony;
import org.loudsi.simulation.implem.ant.context.Pheromone;
import org.loudsi.visual.jfx.renderer.DrawUtils;

import java.util.HashMap;

public class AntColorManager {

    private static HashMap<String, Color> colorByColoniesNames = new HashMap<>();
    private static boolean multiColony = false;

    public static Color getColonyColor(AntColony colony) {
        if (multiColony) {
            return colorByColoniesNames.computeIfAbsent(colony.getName(), k -> DrawUtils.generateRandomColor());
        } else {
            return Color.BLACK;
        }

    }

    public static Color getAntColor(AntColony colony) {
        if (multiColony) {
            return getColonyColor(colony);
        } else {
            return Color.GREY;
        }
    }

    public static Color getPheromoneColor(AntColony antColony, Pheromone pheromone) {
        if (multiColony) {
            return DrawUtils.copyWithOpacity(getColonyColor(antColony), 0.5);
        } else {
            switch (pheromone.getPheromoneType()) {
                case FIND_FOOD:
                    return Color.BLUE;
                case BRING_BACK_FOOD:
                    return Color.GREEN;
                default:
                    return Color.RED;
            }
        }

    }

    public static Color getFoodColor() {
        return Color.ORANGE;
    }

    public static Paint getAntDetectionRadiusColor() {
        return DrawUtils.copyWithOpacity(Color.BLACK, 0.2);
    }
}
