package org.loudsi.simulation.implem.util;

import org.loudsi.common.Pair;
import org.loudsi.common.RandomHelper;

import java.util.Collection;

public class Vector {


    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector from(Position source, Position destination) {
        return new Vector(destination.getX() - source.getX(), destination.getY() - source.getY());
    }

    public static Vector resultingPondered(Collection<Pair<Vector, Double>> vectorWithStrength) {

        double resultingX = 0;
        double resultingY = 0;

        for (Pair<Vector, Double> vectors : vectorWithStrength) {
            final Vector vector = vectors.getFirst().normalise();
            final double strength = vectors.getSecond();
            resultingX = resultingX + (strength * vector.getX());
            resultingY = resultingY + (strength * vector.getY());
        }
        return new Vector(resultingX, resultingY).normalise();
    }

    public static Vector createRandom() {
        return new Vector(RandomHelper.randomDoubleInclusive(-1, 1), RandomHelper.randomDoubleInclusive(-1, 1));
    }

    public Vector negate() {
        return new Vector(-this.x, -this.y);
    }

    public double getLenght() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector normalise() {

        double length = getLenght();
        if (length != 0) {
            return new Vector(this.x / length, this.y / length);
        } else {
            return this;
        }

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (Double.compare(vector.x, x) != 0) return false;
        return Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
