package org.loudsi.simulation.implem.genetic.ant.context;


import org.loudsi.simulation.implem.util.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AntColony implements Serializable {
    private final String name;
    private final Position position;
    private final List<Ant> ants = new ArrayList<>();
    private List<Pheromone> pheromones = new ArrayList<>();

    private int colonyFood;

    public AntColony(String name, Position position, int colonyFood) {
        this.name = name;
        this.position = position;
        this.colonyFood = colonyFood;
    }

    public int getColonyFood() {
        return colonyFood;
    }

    public void setColonyFood(int colonyFood) {
        this.colonyFood = colonyFood;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public List<Pheromone> getPheromones() {
        return pheromones;
    }

    @Override
    public String toString() {
        return "AntColony{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", ants=" + ants +
                ", pheromones=" + pheromones +
                ", colonyFood=" + colonyFood +
                '}';
    }
}
