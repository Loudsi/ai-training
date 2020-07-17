package org.loudsi.visual.jfx.api;

import javafx.stage.Stage;

public class StageHolder {

    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
