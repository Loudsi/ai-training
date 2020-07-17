package org.loudsi.simulation.implem.ant.context;

import org.loudsi.simulation.implem.util.Position;
import org.loudsi.simulation.implem.util.Vector;

import java.io.Serializable;
import java.util.HashMap;

public class Ant implements Serializable {

    private final String name;
    private final double AntActionRadius;
    private final HashMap<AntAction, Double> scentDetectionRadius;
    private final HashMap<AntAction, Integer> putPheromoneFrequencies;
    private final HashMap<AntAction, Integer> pheromoneStrength;
    private Position position;
    private Vector vector;
    private AntAction antAction;
    private boolean haveFood;


    public Ant(String name, Position position, Vector vector, AntAction antAction, HashMap<AntAction, Double> scentDetectionRadius, double tAntActionRadius, HashMap<AntAction, Integer> putPheromoneFrequencies, HashMap<AntAction, Integer> pheromoneStrenght) {
        this.name = name;
        this.position = position;
        this.vector = vector;
        this.antAction = antAction;
        this.scentDetectionRadius = scentDetectionRadius;
        this.AntActionRadius = tAntActionRadius;
        this.putPheromoneFrequencies = putPheromoneFrequencies;
        this.pheromoneStrength = pheromoneStrenght;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public AntAction getAntAction() {
        return antAction;
    }

    public void setAntAction(AntAction antAction) {
        this.antAction = antAction;
    }

    public void getFood() {
        this.haveFood = true;
    }

    public void dropFood() {
        this.haveFood = false;
    }

    public double getScentDetectionRadius() {
        return scentDetectionRadius.get(this.antAction);
    }

    public double getAntActionRadius() {
        return AntActionRadius;
    }

    public boolean isHaveFood() {
        return haveFood;
    }

    public int getPutPheromoneFrequencies() {
        return putPheromoneFrequencies.get(antAction);
    }

    public int getPheromoneStrength() {
        return this.pheromoneStrength.get(antAction);
    }
}
