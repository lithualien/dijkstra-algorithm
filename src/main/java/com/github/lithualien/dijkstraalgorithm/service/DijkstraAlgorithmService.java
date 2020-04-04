package com.github.lithualien.dijkstraalgorithm.service;

import com.github.lithualien.dijkstraalgorithm.entity.Coordinates;
import com.github.lithualien.dijkstraalgorithm.entity.ShortestPath;

public interface DijkstraAlgorithmService {
    ShortestPath findShortestPath(Coordinates coordinates);
}
