package org.loudsi.visual.implem.ant.engine.visual;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.loudsi.simulation.implem.genetic.ant.context.Ant;
import org.loudsi.simulation.implem.genetic.ant.context.AntColony;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;
import org.loudsi.simulation.implem.genetic.ant.context.AntFood;
import org.loudsi.visual.jfx.renderer.DrawUtils;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AntContextRenderer implements IRenderer<AntContext> {

    private HashMap<AntCanvasNames, Integer> renderNumber = new HashMap<>();

    @Override
    public HashMap<ICanvasName, Canvas> registerCanvas() {
        HashMap<ICanvasName, Canvas> allCanvas = new HashMap<>();
        allCanvas.put(AntCanvasNames.COLONY_CANVAS, new Canvas(AntContext.WIDTH, AntContext.LENGTH));
        allCanvas.put(AntCanvasNames.ANT_CANVAS, new Canvas(AntContext.WIDTH, AntContext.LENGTH));
        allCanvas.put(AntCanvasNames.PHEROMONES_CANVAS, new Canvas(AntContext.WIDTH, AntContext.LENGTH));
        allCanvas.put(AntCanvasNames.FOOD_CANVAS, new Canvas(AntContext.WIDTH, AntContext.LENGTH));
        return allCanvas;
    }

    @Override
    public void drawVisuals(AntContext context, Map<ICanvasName, Canvas> canvas) {
        if (renderNumber.computeIfAbsent(AntCanvasNames.COLONY_CANVAS, k -> 0) <= 0) {
            final Optional<Canvas> optionalColonyCanvas = Optional.ofNullable(canvas.get(AntCanvasNames.COLONY_CANVAS));
            optionalColonyCanvas.ifPresent(colonyCanvas -> {
                this.drawColony(context, colonyCanvas);
                renderNumber.put(AntCanvasNames.COLONY_CANVAS, renderNumber.get(AntCanvasNames.COLONY_CANVAS) + 1);
            });

        }
        final Optional<Canvas> optionalFoodCanvas = Optional.ofNullable(canvas.get(AntCanvasNames.FOOD_CANVAS));
        optionalFoodCanvas.ifPresent(foodCanvas -> drawFood(context, foodCanvas));

        final Optional<Canvas> optionalPheromoneCanvas = Optional.ofNullable(canvas.get(AntCanvasNames.PHEROMONES_CANVAS));
        optionalPheromoneCanvas.ifPresent(pheromoneCanvas -> drawPheromones(context, pheromoneCanvas));

        final Optional<Canvas> optionalAntCanvas = Optional.ofNullable(canvas.get(AntCanvasNames.ANT_CANVAS));
        optionalAntCanvas.ifPresent(antCanvas -> drawAnts(context, antCanvas));


    }

    private void drawFood(AntContext context, Canvas canvas) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        DrawUtils.clearCanvas(canvas);
        gc.setFill(AntColorManager.getFoodColor());
        for (AntFood antFood : context.getFood()) {
            double foodWidth = 4;
            double foodHeight = 4;
            gc.fillOval(antFood.getPosition().getX() - (foodWidth / 2), antFood.getPosition().getY() - (foodHeight / 2), foodWidth, foodHeight);
        }
    }

    private void drawPheromones(AntContext world, Canvas pheromonesCanvas) {
        final GraphicsContext gc = pheromonesCanvas.getGraphicsContext2D();
        DrawUtils.clearCanvas(pheromonesCanvas);
        world.getColonies().forEach(antColony -> {
            antColony.getPheromones().forEach(pheromone -> {
                gc.setFill(AntColorManager.getPheromoneColor(antColony, pheromone));
                gc.fillOval(pheromone.getPosition().getX(), pheromone.getPosition().getY(), 2, 2);
            });
        });
    }

    private void drawAnts(AntContext world, Canvas antCanvas) {
        final GraphicsContext gc = antCanvas.getGraphicsContext2D();
        DrawUtils.clearCanvas(antCanvas);
        for (AntColony colony : world.getColonies()) {
            gc.setFill(AntColorManager.getAntColor(colony));
            for (Ant ant : colony.getAnts()) {
                double antWidth = 4;
                double antHeight = 4;
                gc.fillOval(ant.getPosition().getX() - (antWidth / 2), ant.getPosition().getY() - (antHeight / 2), antWidth, antHeight);
                drawAntScentRadius(ant, gc);
            }
        }
    }

    private void drawAntScentRadius(Ant ant, GraphicsContext gc) {
        gc.setStroke(AntColorManager.getAntDetectionRadiusColor());
        gc.strokeOval(ant.getPosition().getX() - (ant.getScentDetectionRadius()), ant.getPosition().getY() - (ant.getScentDetectionRadius()), ant.getScentDetectionRadius() * 2, ant.getScentDetectionRadius() * 2);
    }

    private void drawColony(AntContext world, Canvas colonyCanvas) {
        final GraphicsContext gc = colonyCanvas.getGraphicsContext2D();
        for (AntColony colony : world.getColonies()) {
            gc.setFill(AntColorManager.getColonyColor(colony));
            double colonyWidth = 10;
            double colonyHeight = 10;
            gc.fillOval(colony.getPosition().getX() - colonyWidth / 2, colony.getPosition().getY() - colonyHeight / 2, colonyWidth, colonyHeight);
        }
    }

}
