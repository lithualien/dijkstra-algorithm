package com.github.lithualien.dijkstraalgorithm.entity;

import java.util.Arrays;
import java.util.List;

public class Coordinates {

    private int beginningCoordinate;
    private int endingCoordinate;
    private int numberOfValues;
    private int[][] coordinates;

    public Coordinates() {
    }

    public Coordinates(int beginningCoordinate, int endingCoordinate) {
        this.beginningCoordinate = beginningCoordinate;
        this.endingCoordinate = endingCoordinate;
    }

    public int getBeginningCoordinate() {
        return beginningCoordinate;
    }

    public void setBeginningCoordinate(int beginningCoordinate) {
        this.beginningCoordinate = beginningCoordinate;
    }

    public int getEndingCoordinate() {
        return endingCoordinate;
    }

    public void setEndingCoordinate(int endingCoordinate) {
        this.endingCoordinate = endingCoordinate;
    }

    public int getNumberOfValues() {
        return numberOfValues;
    }

    public void setNumberOfValues(int numberOfValues) {
        this.numberOfValues = numberOfValues;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "beginningCoordinate=" + beginningCoordinate +
                ", endingCoordinate=" + endingCoordinate +
                ", numberOfValues=" + numberOfValues +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
