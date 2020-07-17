package org.loudsi.visual;

import javafx.application.Application;
import org.loudsi.visual.jfx.api.FXApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        Application.launch(FXApp.class, args);
    }
}
