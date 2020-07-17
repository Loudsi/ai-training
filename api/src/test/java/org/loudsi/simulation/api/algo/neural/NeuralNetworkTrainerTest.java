package org.loudsi.simulation.api.algo.neural;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.loudsi.simulation.api.algo.neural.fonction.activation.Identity;
import org.loudsi.simulation.api.algo.neural.fonction.activation.LeakyReLu;
import org.loudsi.simulation.api.algo.neural.network.NeuralNetwork;
import org.loudsi.simulation.api.algo.neural.trainingdata.TrainingData;
import org.loudsi.simulation.api.algo.neural.trainingdata.TrainingDataBuilder;

import java.util.Set;

class NeuralNetworkTrainerTest {

    private NeuralNetworkBuilder neuralNetworkBuilder = new NeuralNetworkBuilder();
    private TrainingDataBuilder trainingDataBuilder = new TrainingDataBuilder();
    private NeuralNetworkTrainer neuralNetworkTrainer = new NeuralNetworkTrainer();

    @Test
    void trainFromData() throws JsonProcessingException {
        final NeuralNetwork neuralNetwork = neuralNetworkBuilder.buildNeuralNetwork(
                2,
                4,
                4,
                1,
                new Identity(),
                new LeakyReLu(0.01),
                new Identity()
        );
        //final Set<TrainingData> trainingData = trainingDataBuilder.buildSumTrainingData(100,2,-20,20);

        //neuralNetworkTrainer.repeatedStochasticTraining(trainingData, neuralNetwork,0.001,0.0001);

        final Set<TrainingData> trainingData2 = trainingDataBuilder.buildSumTrainingData(100, 2, -10, 10);
        neuralNetworkTrainer.repeatedStochasticTraining(trainingData2, neuralNetwork, 0.001, 0.0001);

        System.out.println(neuralNetwork);
        //final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        //System.out.println(objectMapper.writeValueAsString(neuralNetwork));
    }
}