package org.loudsi.simulation.implem.genetic.ant.manager;

import org.loudsi.simulation.api.algo.genetic.IGeneticManager;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.simulation.implem.genetic.ant.context.Ant;
import org.loudsi.simulation.implem.genetic.ant.context.AntColony;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;
import org.loudsi.simulation.implem.genetic.ant.manager.sub.AntManager;
import org.loudsi.simulation.implem.genetic.ant.manager.sub.PheromonesManager;

public class AntParametrizedGeneticManager implements IGeneticManager<AntContext, AntEngineGeneticConfig> {

    @Override
    public void updateContext(AntContext context, AntEngineGeneticConfig antConfig, int engineCycle) {
        for (AntColony colony : context.getColonies()) {
            AntManager.produceAnt(colony, antConfig);
            PheromonesManager.agePheromone(colony.getPheromones());
        }
        for (AntColony colony : context.getColonies()) {
            for (Ant ant : colony.getAnts()) {
                AntManager.antInteract(ant, colony, context, antConfig);
                AntManager.makeAntDecideDirection(ant, colony, antConfig);
                AntManager.moveAnt(ant);
                AntManager.addAntPheromone(engineCycle, ant, colony.getPheromones());
            }
        }
    }

    @Override
    public boolean isGoalReached(AntContext context) {
        return false;
    }

    @Override
    public int getEngineTickInMs(int engineCycle) {
        return 10;
    }
}
