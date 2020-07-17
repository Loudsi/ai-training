package org.loudsi.simulation.api.ai.neural.trainingdata;

import org.loudsi.common.RandomHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TrainingDataBuilder {

    public Set<TrainingData> buildSumTrainingData(int numberOfData, int inputSize, double min, double max) {
        Set<TrainingData> trainingDataSet = new HashSet<>();
        for (int i = 0; i < numberOfData; i++) {
            ArrayList<Double> inputs = new ArrayList<>();
            for (int j = 0; j < inputSize; j++) {
                inputs.add(RandomHelper.randomDoubleInclusive(min,max));
            }
            ArrayList<Double> output = new ArrayList<>();
            output.add(inputs.stream().mapToDouble(Double::doubleValue).sum());
            trainingDataSet.add(new TrainingData(inputs, output));
        }
        return trainingDataSet;
    }

    public Set<TrainingData> buildStaticTrainingData(int numberOfData) {
        Set<TrainingData> trainingDataSet = new HashSet<>();
        for (int i = 0; i < numberOfData; i++) {
            ArrayList<Double> inputs = new ArrayList<>();
            inputs.add(5D);
            inputs.add(3D);
            ArrayList<Double> output = new ArrayList<>();
            output.add(8D);
            trainingDataSet.add(new TrainingData(inputs, output));
        }
        return trainingDataSet;
    }

    public Set<TrainingData> buildTestData(int numberOfData) {
        Set<TrainingData> trainingDataSet = new HashSet<>();
        for (int i = 0; i < numberOfData; i++) {
            ArrayList<Double> inputs = new ArrayList<>();
            inputs.add(1D);
            inputs.add(10D);
            ArrayList<Double> output = new ArrayList<>();
            output.add(1D);
            output.add(0D);
            trainingDataSet.add(new TrainingData(inputs, output));
        }
        return trainingDataSet;
    }
}
