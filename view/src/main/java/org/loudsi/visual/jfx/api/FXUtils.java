package org.loudsi.visual.jfx.api;

import javafx.animation.AnimationTimer;

public class FXUtils {
    private static long lastUpdate = 0;
    private static int index = 0;
    private static final double[] frameRates = new double[100];

    static {
        AnimationTimer frameRateMeter = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    long nanosElapsed = now - lastUpdate;
                    double frameRate = 1000000000.0 / nanosElapsed;
                    index %= frameRates.length;
                    frameRates[index++] = frameRate;
                }

                lastUpdate = now;
            }
        };

        frameRateMeter.start();
    }


    public static double getInstantFPS() {
        return frameRates[index % frameRates.length];
    }


    public static double getAverageFPS() {
        double total = 0.0d;

        for (double frameRate : frameRates) {
            total += frameRate;
        }

        return total / frameRates.length;
    }
}