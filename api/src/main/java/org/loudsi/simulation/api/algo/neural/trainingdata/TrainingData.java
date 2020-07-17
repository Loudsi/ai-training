package org.loudsi.simulation.api.algo.neural.trainingdata;

import java.util.ArrayList;

public class TrainingData {

    private ArrayList<Double> inputValues;
    private ArrayList<Double> desiredOutput;

    public TrainingData(ArrayList<Double> inputValues, ArrayList<Double> desiredOutput) {
        this.inputValues = inputValues;
        this.desiredOutput = desiredOutput;
    }

    public ArrayList<Double> getInputValues() {
        return inputValues;
    }

    public void setInputValues(ArrayList<Double> inputValues) {
        this.inputValues = inputValues;
    }

    public ArrayList<Double> getDesiredOutput() {
        return desiredOutput;
    }

    public void setDesiredOutput(ArrayList<Double> desiredOutput) {
        this.desiredOutput = desiredOutput;
    }
}
