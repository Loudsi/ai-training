package org.loudsi.visual.implem.ant.engine.visual;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.loudsi.simulation.implem.genetic.ant.context.AntColony;
import org.loudsi.simulation.implem.genetic.ant.context.Pheromone;
import org.loudsi.visual.jfx.renderer.DrawUtils;

import java.util.HashMap;

public class AntColorManager {

    private static final HashMap<String, Color> colorByColoniesNames = new HashMap<>();
    private static final boolean multiColony = false;

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
            return switch (pheromone.getPheromoneType()) {
                case FIND_FOOD -> Color.BLUE;
                case BRING_BACK_FOOD -> Color.GREEN;
                default -> Color.RED;
            };
        }

    }

    public static Color getFoodColor() {
        return Color.ORANGE;
    }

    public static Paint getAntDetectionRadiusColor() {
        return DrawUtils.copyWithOpacity(Color.BLACK, 0.2);
    }
}
