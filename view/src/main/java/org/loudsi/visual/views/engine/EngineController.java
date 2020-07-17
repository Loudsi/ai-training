package org.loudsi.visual.views.engine;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import org.loudsi.visual.adapter.engine.AbstractEngineAdapter;
import org.loudsi.visual.jfx.api.StageHolder;
import org.loudsi.visual.jfx.renderer.DrawUtils;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.views.RunnableControllerHelper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EngineController {

    private final AbstractEngineAdapter engineAdapter;

    public VBox engineCanvasHolder;
    public VBox canvasSelector;

    public Button engineStartStopButton;
    public Button enginePauseResumeButton;

    private Set<String> visibleCanvas;


    public EngineController(AbstractEngineAdapter engineAdapter) {
        this.engineAdapter = engineAdapter;
    }

    @FXML
    public void initialize() {
        Group engineGroup = new Group();

        final Map<ICanvasName, Canvas> registeredCanvas = engineAdapter.registerCanvas();

        engineGroup.getChildren().addAll(registeredCanvas.values());

        StageHolder.getMainStage().setOnCloseRequest(event -> engineAdapter.stop());

        final AnimationTimer engineAnimation = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                final Map<ICanvasName, Canvas> canvasToDReset = registeredCanvas.entrySet()
                        .stream()
                        .filter(iCanvasNameCanvasEntry -> !visibleCanvas.contains(iCanvasNameCanvasEntry.getKey().toString()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                canvasToDReset.values().forEach(DrawUtils::clearCanvas);

                final Map<ICanvasName, Canvas> canvasToDraw = registeredCanvas.entrySet()
                        .stream()
                        .filter(iCanvasNameCanvasEntry -> visibleCanvas.contains(iCanvasNameCanvasEntry.getKey().toString()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                engineAdapter.drawVisuals(canvasToDraw);

            }
        };
        engineAnimation.start();

        enginePauseResumeButton.setDisable(true);
        canvasSelector.getChildren().addAll(addCanvasCheckBox(registeredCanvas));
        engineCanvasHolder.getChildren().addAll(engineGroup);
        this.visibleCanvas = registeredCanvas.keySet().stream().map(Objects::toString).collect(Collectors.toSet());
    }

    private Collection<? extends Node> addCanvasCheckBox(Map<ICanvasName, Canvas> registeredCanvas) {
        return registeredCanvas.keySet().stream().map(
                canvasName -> {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(true);
                    checkBox.setText(canvasName.toString());
                    checkBox.setOnAction(this::handleCheckBoxAction);
                    return checkBox;
                }
        ).collect(Collectors.toList());
    }

    public void handleCheckBoxAction(ActionEvent actionEvent) {
        CheckBox clickedCheckBox = (CheckBox) actionEvent.getSource();
        if (clickedCheckBox.isSelected()) {
            visibleCanvas.add(clickedCheckBox.getText());
        } else {
            visibleCanvas.remove(clickedCheckBox.getText());
        }
    }

    public void handleStartStopEngine(ActionEvent actionEvent) {
        RunnableControllerHelper.handleStartStopRunnable(engineAdapter, enginePauseResumeButton, engineStartStopButton);
    }


    public void handlePauseResumeEngine(ActionEvent actionEvent) {
        RunnableControllerHelper.handlePauseResumeRunnable(engineAdapter, enginePauseResumeButton, engineStartStopButton);
    }
}
