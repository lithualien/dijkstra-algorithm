package com.github.lithualien.dijkstraalgorithm.service;

import com.github.lithualien.dijkstraalgorithm.entity.Coordinates;
import com.github.lithualien.dijkstraalgorithm.entity.ShortestPath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DijkstraAlgorithmServiceImpl implements DijkstraAlgorithmService {

    private List<Integer> answer;
    private int[] parents;
    private final int NO_PARENT = -1;
    private int distance;

    @Override
    public ShortestPath findShortestPath(Coordinates coordinates) {
        dijkstra(coordinates.getCoordinates(), coordinates.getBeginningCoordinate()-1, coordinates.getEndingCoordinate()-1);
        answer = new ArrayList<>();
        printPath(coordinates.getEndingCoordinate()-1, parents);
        return new ShortestPath(distance, answer);
    }

    private void dijkstra(int[][] adjacencyMatrix, int startVertex, int endVertex) {
        int nVertices = adjacencyMatrix[0].length;
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
        shortestDistances[startVertex] = 0;
        parents = new int[nVertices];
        parents[startVertex] = NO_PARENT;
        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        distance = shortestDistances[endVertex];
    }

    private void printPath(int currentVertex, int[] parents) {
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        answer.add(currentVertex+1);
    }
}
