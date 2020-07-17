package org.loudsi.simulation.implem.ant.context;


import org.loudsi.simulation.implem.util.Position;

import java.io.Serializable;

public class Pheromone implements Serializable {

    private final Position position;
    private final AntAction pheromoneType;

    private double strenght;

    public Pheromone(Position position, int strenght, AntAction pheromoneType) {

        this.position = position;
        this.strenght = strenght;
        this.pheromoneType = pheromoneType;
    }


    public Position getPosition() {
        return position;
    }

    public double getStrenght() {
        return strenght;
    }

    public void setStrenght(double strenght) {
        this.strenght = strenght;
    }

    public AntAction getPheromoneType() {
        return pheromoneType;
    }
}
