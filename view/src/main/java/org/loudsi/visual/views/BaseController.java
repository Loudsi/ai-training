package org.loudsi.visual.views;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.loudsi.visual.views.engine.EngineController;
import org.loudsi.visual.views.training.TrainingController;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @FXML
    private TabPane tabPane;

    //###################################Inject part#############################################
    // Inject tab content
    @FXML
    private Tab engineTab;
    @FXML
    private Tab trainerTab;

    // Inject tab controller
    @FXML
    private EngineController engineController;
    @FXML
    private TrainingController trainerController;
    //###########################################################################################

    public void init() {
        System.out.println("Init tabs");
    }
}