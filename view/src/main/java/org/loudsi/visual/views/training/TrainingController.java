package org.loudsi.visual.views.training;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.loudsi.visual.adapter.IVisualRunnerAdapter;
import org.loudsi.visual.adapter.trainer.AbstractTrainerAdapter;
import org.loudsi.visual.jfx.api.StageHolder;
import org.loudsi.visual.jfx.renderer.ICanvasName;
import org.loudsi.visual.views.RunnableControllerHelper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TrainingController {

    private final IVisualRunnerAdapter trainerAdapter;

    public Button trainerStartStopButton;
    public Button trainerPauseResumeButton;
    public VBox trainerCanvasHolder;

    public TrainingController(AbstractTrainerAdapter trainerAdapter) {
        this.trainerAdapter = trainerAdapter;
    }


    @FXML
    public void initialize() {
        Group trainingGroup = new Group();

        final Map<ICanvasName, Canvas> registeredCanvas = trainerAdapter.registerCanvas();

        trainingGroup.getChildren().addAll(registeredCanvas.values());

        StageHolder.getMainStage().setOnCloseRequest(event -> trainerAdapter.stop());

        final AnimationTimer engineAnimation = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                trainerAdapter.drawVisuals(registeredCanvas);
            }
        };
        engineAnimation.start();
        trainerCanvasHolder.getChildren().addAll(trainingGroup);
        trainerPauseResumeButton.setDisable(true);
    }


    public void handleStartStopTrainer(ActionEvent actionEvent) {
        RunnableControllerHelper.handleStartStopRunnable(trainerAdapter, trainerPauseResumeButton, trainerStartStopButton);
    }

    public void handlePauseResumeTrainer(ActionEvent actionEvent) {
        RunnableControllerHelper.handlePauseResumeRunnable(trainerAdapter, trainerPauseResumeButton, trainerStartStopButton);
    }

}
