package org.loudsi.simulation.implem.util;

import java.io.Serializable;

public class Position implements Serializable {

    private double x;
    private double y;

    public Position(double x, double y) {
        assert !Double.isNaN(x);
        assert !Double.isNaN(y);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Position clone() {
        return new Position(this.getX(), this.getY());
    }

}
