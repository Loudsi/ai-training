package org.loudsi.simulation.implem.ant.config;

import org.loudsi.common.RandomHelper;
import org.loudsi.simulation.api.ai.genetic.IGeneticConfigurationGenerator;
import org.loudsi.simulation.implem.ant.context.AntAction;

import java.util.HashMap;

public class AntConfigGenerator implements IGeneticConfigurationGenerator<AntEngineConfig> {

    public AntEngineConfig generate(int interval) {
        HashMap<AntAction, BehaviorConfig> behaviorConfigHashMap = new HashMap<>();
        for (AntAction behaviorAction : AntAction.values()) {
            behaviorConfigHashMap.put(
                    behaviorAction,
                    new BehaviorConfig(
                            RandomHelper.randomDoubleInclusive(interval),
                            RandomHelper.randomDoubleInclusive(interval),
                            RandomHelper.randomDoubleInclusive(interval),
                            generateRandomMapOfDouble(interval))
            );
        }
        return new AntEngineConfig(
                behaviorConfigHashMap,
                generateRandomMapOfDouble(interval),
                generateRandomMapOfInt(interval),
                generateRandomMapOfInt(interval)
        );
    }

    public AntEngineConfig mutateFrom(AntEngineConfig previousConfig, double variationAmount) {
        HashMap<AntAction, BehaviorConfig> behaviorConfigHashMap = new HashMap<>();

        for (AntAction behaviorAction : AntAction.values()) {
            behaviorConfigHashMap.put(behaviorAction,
                    new BehaviorConfig(
                            previousConfig.getAntBehaviorConfig().get(behaviorAction).getToColonyWeight() + RandomHelper.randomDoubleInclusive(variationAmount),
                            previousConfig.getAntBehaviorConfig().get(behaviorAction).getInertiaWeight() + RandomHelper.randomDoubleInclusive(variationAmount),
                            previousConfig.getAntBehaviorConfig().get(behaviorAction).getRandomness() + RandomHelper.randomDoubleInclusive(variationAmount),
                            generateFromRandomMapOfDouble(previousConfig.getAntBehaviorConfig().get(behaviorAction).getAntActionWeight(), variationAmount)
                    )
            );
        }

        return new AntEngineConfig(
                behaviorConfigHashMap,
                generateFromRandomMapOfDouble(previousConfig.getAntScentDetectionRadiusConfig(), variationAmount),
                generateFromRandomMapOfInt(previousConfig.getAntPheromoneDepositFrequencyConfig(), (int) (variationAmount)),
                generateFromRandomMapOfInt(previousConfig.getAntPheromoneStrenghtConfig(), (int) (variationAmount))
        );
    }

    private HashMap<AntAction, Double> generateRandomMapOfDouble(double interval) {
        HashMap<AntAction, Double> map = new HashMap<>();
        for (AntAction value : AntAction.values()) {
            map.put(value, RandomHelper.randomDoubleInclusive(interval));
        }
        return map;
    }

    private HashMap<AntAction, Double> generateFromRandomMapOfDouble(HashMap<AntAction, Double> previous, double interval) {
        HashMap<AntAction, Double> newMap = new HashMap<>();
        previous.forEach((key, value) -> newMap.put(key, value + RandomHelper.randomDoubleInclusive(interval)));
        return newMap;
    }

    private HashMap<AntAction, Integer> generateFromRandomMapOfInt(HashMap<AntAction, Integer> previous, int interval) {
        HashMap<AntAction, Integer> newMap = new HashMap<>();
        previous.forEach((key, value) -> newMap.put(key, value + RandomHelper.randomIntInclusive(interval)));
        return newMap;
    }

    private HashMap<AntAction, Integer> generateRandomMapOfInt(int interval) {
        HashMap<AntAction, Integer> map = new HashMap<>();
        for (AntAction value : AntAction.values()) {
            map.put(value, RandomHelper.randomIntInclusive(interval));
        }
        return map;
    }
}
