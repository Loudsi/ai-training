package org.loudsi.visual.implem.ant.trainer.visual;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.loudsi.common.Pair;
import org.loudsi.common.tree.Node;
import org.loudsi.common.tree.NodeHelper;
import org.loudsi.simulation.api.training.LearningRunsResult;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.visual.jfx.renderer.DrawUtils;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AntTreeBasedContextRenderer implements IRenderer<Node<LearningRunsResult<AntEngineGeneticConfig>>> {

    public static final double WIDTH = 1000;
    public static final double HEIGHT = 700;
    public static final double CIRCLE_DIAMETER = 15;
    public static final int HEIGHT_SPACING = 35;


    @Override
    public HashMap<ICanvasName, Canvas> registerCanvas() {
        return new HashMap<>() {{
            put(ATCanvas.MAIN, new Canvas(WIDTH, HEIGHT));
        }};
    }

    @Override
    public void drawVisuals(Node<LearningRunsResult<AntEngineGeneticConfig>> context, Map<ICanvasName, Canvas> canvas) {
        final Optional<Canvas> mainCanvas = Optional.ofNullable(canvas.get(ATCanvas.MAIN));
        mainCanvas.ifPresent(mainCanva -> this.drawMainCanvas(context, mainCanva));
    }

    private void drawMainCanvas(Node<LearningRunsResult<AntEngineGeneticConfig>> context, Canvas canvas) {
        DrawUtils.clearCanvas(canvas);
        final GraphicsContext context2D = canvas.getGraphicsContext2D();
        if (context != null) {
            Map<Integer, Integer> childByDepth = NodeHelper.getChildrenByDepth(context);
            Map<Integer, Integer> drawnedByDepth = new HashMap<>();
            this.drawNodes(context, context2D, drawnedByDepth, childByDepth, 0, null);
        }
    }

    private void drawNodes(Node<LearningRunsResult<AntEngineGeneticConfig>> node, GraphicsContext context2D, Map<Integer, Integer> drowedByDepth, Map<Integer, Integer> childByDepth, int depth, Pair<Double, Double> parentPosition) {
        if (node.isLeaf()) {
            context2D.setFill(Color.GREEN);
        } else {
            context2D.setFill(Color.BLUE);
        }

        Integer drawedNumber = drowedByDepth.get(depth);
        if (drawedNumber == null) {
            drawedNumber = 0;
        }

        double x = (WIDTH / (1 + childByDepth.get(depth))) + drawedNumber * (WIDTH / (1 + childByDepth.get(depth))) - (CIRCLE_DIAMETER / 2);
        double y = (depth * (CIRCLE_DIAMETER + HEIGHT_SPACING)) - (CIRCLE_DIAMETER / 2);

        drowedByDepth.put(depth, drawedNumber + 1);

        if (parentPosition != null) {
            context2D.setStroke(Color.GRAY);
            context2D.strokeLine(x + (CIRCLE_DIAMETER / 2), y + (CIRCLE_DIAMETER / 2), parentPosition.getFirst(), parentPosition.getSecond());
        }

        context2D.fillOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        if (node.getData() != null) {

            context2D.fillText(String.format("%.2f", node.getData().getScore()), x, y);
        }
        node.getChildren().forEach(child -> this.drawNodes(child, context2D, drowedByDepth, childByDepth, depth + 1, new Pair<Double, Double>(x + (CIRCLE_DIAMETER / 2), y + (CIRCLE_DIAMETER / 2))));
    }

    private enum ATCanvas implements ICanvasName {
        MAIN
    }
}
