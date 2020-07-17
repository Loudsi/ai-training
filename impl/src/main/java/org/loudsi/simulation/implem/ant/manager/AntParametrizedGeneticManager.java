package org.loudsi.simulation.implem.ant.manager;

import org.loudsi.simulation.api.engine.IGeneticManager;
import org.loudsi.simulation.implem.ant.config.AntEngineConfig;
import org.loudsi.simulation.implem.ant.context.Ant;
import org.loudsi.simulation.implem.ant.context.AntColony;
import org.loudsi.simulation.implem.ant.context.AntContext;
import org.loudsi.simulation.implem.ant.manager.sub.AntManager;
import org.loudsi.simulation.implem.ant.manager.sub.PheromonesManager;

public class AntParametrizedGeneticManager implements IGeneticManager<AntContext, AntEngineConfig> {

    @Override
    public void updateContext(AntContext context, AntEngineConfig antConfig, int engineCycle) {
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
