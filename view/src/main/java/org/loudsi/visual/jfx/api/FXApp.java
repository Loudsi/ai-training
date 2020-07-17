package org.loudsi.visual.jfx.api;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.loudsi.visual.Launcher;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class FXApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(Launcher.class)
                .run();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return (Stage) this.getSource();
        }
    }
}
