package org.loudsi.simulation.api.ai.neural;

import org.loudsi.simulation.api.ai.neural.network.NeuralNetwork;
import org.loudsi.simulation.api.ai.neural.trainingdata.TrainingData;

import java.util.Set;

public class NeuralNetworkTrainer {

    private final NeuralNetworkBuilder neuralNetworkBuilder = new NeuralNetworkBuilder();

    public void stochasticTraining(Set<TrainingData> dataset, NeuralNetwork neuralNetwork, double learningRate) {
        runTrainingSet(dataset, neuralNetwork,learningRate);
    }

    public void repeatedStochasticTraining(Set<TrainingData> dataset, NeuralNetwork neuralNetwork, double targetCost, double learningRate) {
        double actualAverageCost = Double.POSITIVE_INFINITY;
        while (actualAverageCost > targetCost){
            actualAverageCost = runTrainingSet(dataset, neuralNetwork, learningRate);
            System.out.println("Average Cost : " + actualAverageCost);
        }

    }

    private double runTrainingSet(Set<TrainingData> dataset, NeuralNetwork neuralNetwork, double learningRate) {
        double trainingSetAverageCost = 0;
        for (TrainingData trainingData : dataset) {
            neuralNetwork.setInputValues(trainingData.getInputValues());
            neuralNetwork.calculateNeuralNetworkOutput();
            final Double dataCost = neuralNetwork.propagateError(trainingData.getDesiredOutput());

            trainingSetAverageCost += dataCost;
            if (learningRate > 0) {
                neuralNetwork.updateWeights(learningRate);
            }
            neuralNetwork.resetNeurons();
        }
        return trainingSetAverageCost / dataset.size();
    }
}
