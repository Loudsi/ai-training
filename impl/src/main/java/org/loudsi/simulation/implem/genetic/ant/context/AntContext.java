package org.loudsi.simulation.implem.genetic.ant.context;

import java.util.List;

public class AntContext {

    public static final double LENGTH = 500;
    public static final double WIDTH = 500;

    private final List<AntColony> colonies;
    private final List<AntFood> food;

    public AntContext(List<AntColony> colonies, List<AntFood> food) {
        this.colonies = colonies;
        this.food = food;
    }

    public List<AntColony> getColonies() {
        return colonies;
    }

    public List<AntFood> getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "AntWorld{" +
                "colonies=" + colonies +
                ", food=" + food +
                '}';
    }
}
