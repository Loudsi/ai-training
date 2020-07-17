package org.loudsi.simulation.implem.genetic.ant.manager.sub;

import org.loudsi.common.Pair;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.simulation.implem.genetic.ant.config.BehaviorConfig;
import org.loudsi.simulation.implem.genetic.ant.context.*;
import org.loudsi.simulation.implem.util.Position;
import org.loudsi.simulation.implem.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class AntManager {

    public static void moveAnt(Ant ant) {
        final Position newPosition = PositionManager.apply(ant.getPosition(), ant.getVector());
        if (PositionManager.isPositionInTheWorld(newPosition)) {
            ant.setPosition(newPosition);
        }
    }

    public static void addAntPheromone(int cycle, Ant ant, List<Pheromone> pheromones) {
        if (cycle % (Math.abs(ant.getPutPheromoneFrequencies()) + 1) == 0) {
            pheromones.add(new Pheromone(ant.getPosition().clone(), Math.abs(ant.getPheromoneStrength()), ant.getAntAction()));
        }
    }

    public static void makeAntDecideDirection(Ant ant, AntColony colony, AntEngineGeneticConfig antEngineGeneticConfig) {
        Set<Pheromone> pheromones = PheromonesManager.getPheromonesAroundAnt(ant, colony.getPheromones(), ant.getScentDetectionRadius());
        final Map<AntAction, List<Pheromone>> pheromoneByType = pheromones.stream().collect(Collectors.groupingBy(Pheromone::getPheromoneType));
        final Map<AntAction, Vector> vectorsByPheromone = calcPheromoneVector(ant, pheromoneByType);


        List<Pair<Vector, Double>> vectorByInterest = new ArrayList<>();
        final AntAction antCurrentAction = ant.getAntAction();
        final BehaviorConfig config = antEngineGeneticConfig.getAntBehaviorConfig().get(antCurrentAction);
        if (config != null) {
            vectorByInterest.add(new Pair<>(Vector.from(ant.getPosition(), colony.getPosition()), config.getToColonyWeight()));
            vectorByInterest.add(new Pair<>(ant.getVector(), config.getInertiaWeight()));
            vectorByInterest.add(new Pair<>(Vector.createRandom(), config.getRandomness()));

            for (AntAction possibleActions : AntAction.values()) {
                final Vector antActionPheromoneVector = vectorsByPheromone.get(possibleActions);
                if (antActionPheromoneVector != null) {
                    vectorByInterest.add(new Pair<>(antActionPheromoneVector, config.getAntActionWeight(possibleActions)));
                }
            }
            final Vector finalVector = Vector.resultingPondered(vectorByInterest).normalise();
            ant.setVector(finalVector);
        } else {
            //TODO add warn log
            System.out.println("No configuration found for ant action : " + antCurrentAction);
        }

    }

    private static Map<AntAction, Vector> calcPheromoneVector(Ant ant, Map<AntAction, List<Pheromone>> pheromoneByType) {
        final Map<AntAction, Vector> pheromoneVectors = new HashMap<>();
        for (Map.Entry<AntAction, List<Pheromone>> pheromoneTypeListEntry : pheromoneByType.entrySet()) {
            final List<Pair<Vector, Double>> poundWithVector = new ArrayList<>();
            for (Pheromone pheromone : pheromoneTypeListEntry.getValue()) {
                poundWithVector.add(new Pair<>(Vector.from(ant.getPosition(), pheromone.getPosition()), pheromone.getStrenght()));
            }
            final Vector resultingVector = Vector.resultingPondered(poundWithVector);
            pheromoneVectors.put(pheromoneTypeListEntry.getKey(), resultingVector);
        }
        return pheromoneVectors;
    }


    public static void antInteract(Ant ant, AntColony colony, AntContext world, AntEngineGeneticConfig engineConfig) {
        switch (ant.getAntAction()) {
            case FIND_FOOD:
                final Optional<AntFood> first = world.getFood().stream().filter(f -> PositionManager.isInside(f.getPosition(), ant.getPosition(), ant.getAntActionRadius())).findFirst();
                if (first.isPresent()) {
                    ant.getFood();
                    ant.setAntAction(AntAction.BRING_BACK_FOOD);
                }
                break;
            case BRING_BACK_FOOD:
                if (PositionManager.isInside(colony.getPosition(), ant.getPosition(), ant.getAntActionRadius())) {
                    ant.dropFood();
                    colony.setColonyFood(colony.getColonyFood() + 1);
                    ant.setAntAction(AntAction.FIND_FOOD);
                }
                break;
            default:
                System.out.println("No Actions");
        }
    }

    public static void produceAnt(AntColony colony, AntEngineGeneticConfig config) {
        if (colony.getAnts().size() < 40) {
            colony.getAnts().add(
                    new Ant(
                            "Ant: " + colony.getAnts().size(),
                            colony.getPosition().clone(),
                            Vector.createRandom().normalise(),
                            AntAction.FIND_FOOD,
                            config.getAntScentDetectionRadiusConfig(),
                            10,
                            config.getAntPheromoneDepositFrequencyConfig(),
                            config.getAntPheromoneStrenghtConfig())
            );
        }
    }
}
