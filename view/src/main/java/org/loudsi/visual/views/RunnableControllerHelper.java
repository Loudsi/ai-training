package org.loudsi.visual.views;

import javafx.scene.control.Button;
import org.loudsi.simulation.api.runnables.RunnableStatus;
import org.loudsi.visual.adapter.IVisualRunnerAdapter;

public abstract class RunnableControllerHelper {

    public static void handleStartStopRunnable(IVisualRunnerAdapter adapter, Button pauseResumeButton, Button startStopButton) {
        final RunnableStatus engineStatus = adapter.getStatus();
        if (engineStatus.equals(RunnableStatus.STOPPED) || engineStatus.equals(RunnableStatus.PENDING_START)) {
            adapter.start();
            pauseResumeButton.setDisable(false);
            startStopButton.setText("Stop");
        } else {
            adapter.stop();
            pauseResumeButton.setDisable(true);
            pauseResumeButton.setText("Pause");
            startStopButton.setText("Restart");
        }
    }

    public static void handlePauseResumeRunnable(IVisualRunnerAdapter adapter, Button pauseResumeButton, Button startStopButton) {
        final RunnableStatus engineStatus = adapter.getStatus();
        if (engineStatus.equals(RunnableStatus.RUNNING)) {
            adapter.pause();
            pauseResumeButton.setText("Resume");
        } else {
            adapter.resume();
            pauseResumeButton.setText("Pause");
        }
    }
}
