package org.loudsi.simulation.implem.genetic.ant.context;

import org.loudsi.simulation.implem.util.Position;

public class AntFood {

    private final Position position;

    public AntFood(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}
