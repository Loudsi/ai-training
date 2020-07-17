package org.loudsi.visual.jfx.api;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainStageInitializer implements ApplicationListener<FXApp.StageReadyEvent> {

    private final ApplicationContext applicationContext;

    MainStageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(FXApp.StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            StageHolder.setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(new ClassPathResource("views/mainView.fxml").getURL());
            fxmlLoader.setControllerFactory(this.applicationContext::getBean);
            Parent parent = fxmlLoader.load();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}