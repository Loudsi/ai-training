package org.loudsi.simulation.implem.genetic.ant.manager.sub;


import org.loudsi.simulation.implem.genetic.ant.context.Ant;
import org.loudsi.simulation.implem.genetic.ant.context.Pheromone;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class PheromonesManager {

    public static void agePheromone(List<Pheromone> pheromones) {
        pheromones.forEach(pheromone -> pheromone.setStrenght(pheromone.getStrenght() - 1));
        pheromones.removeIf(pheromone -> pheromone.getStrenght() <= 0);
    }

    //TODO this is where i can win some time
    public static Set<Pheromone> getPheromonesAroundAnt(Ant ant, List<Pheromone> pheromones, double detectionRadius) {
        return pheromones.stream().filter(pheromone -> PositionManager.isInside(pheromone.getPosition(), ant.getPosition(), detectionRadius)).collect(Collectors.toSet());
    }

}
