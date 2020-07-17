package org.loudsi.simulation.implem.genetic.ant;

import org.loudsi.simulation.implem.genetic.ant.context.AntColony;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;
import org.loudsi.simulation.implem.genetic.ant.context.AntFood;
import org.loudsi.simulation.implem.util.Position;

import java.util.List;

public class AntContextBuilder {
    public static AntContext buildAntContext() {
        return new AntContext(
                List.of(
                        //new AntColony("Colony 1", new Position(100, 100), 30),
                        new AntColony("Colony 2", new Position(AntContext.WIDTH / 2, AntContext.LENGTH / 2), 0)
                        // new AntColony("Colony 3", new Position(AntWorld.WIDTH -100, AntWorld.LENGTH -100), 30)
                ),
                List.of(
                        new AntFood(new Position(AntContext.WIDTH / 2, AntContext.LENGTH / 4)),
                        new AntFood(new Position(AntContext.WIDTH / 4, AntContext.LENGTH / 2)),
                        new AntFood(new Position(AntContext.WIDTH / 3, AntContext.LENGTH / 3)),
                        new AntFood(new Position(AntContext.WIDTH / 2, AntContext.LENGTH / 4)),
                        new AntFood(new Position(AntContext.WIDTH - AntContext.WIDTH / 5, AntContext.LENGTH - AntContext.LENGTH / 3)),
                        new AntFood(new Position(AntContext.WIDTH - AntContext.WIDTH / 7, AntContext.LENGTH - AntContext.LENGTH / 6))
                )
        );
    }
}
