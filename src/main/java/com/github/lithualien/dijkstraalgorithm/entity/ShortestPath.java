package com.github.lithualien.dijkstraalgorithm.entity;

import java.util.List;

public class ShortestPath {
    private int sum;
    private List<Integer> path;

    public ShortestPath() {
    }

    public ShortestPath(int sum, List<Integer> path) {
        this.sum = sum;
        this.path = path;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<Integer> getPath() {
        return path;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ShortestPath{" +
                "sum=" + sum +
                ", path=" + path +
                '}';
    }
}
