package com.github.lithualien.dijkstraalgorithm.controller;

import com.github.lithualien.dijkstraalgorithm.entity.Coordinates;
import com.github.lithualien.dijkstraalgorithm.entity.ShortestPath;
import com.github.lithualien.dijkstraalgorithm.service.DijkstraAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class ShortestPathController {

    private DijkstraAlgorithmService dijkstraAlgorithmService;

    @PostMapping("shortest-path")
    public ResponseEntity<ShortestPath> getShortestPath(@RequestBody Coordinates coordinates) {
        dijkstraAlgorithmService.findShortestPath(coordinates);
        return ResponseEntity.ok(dijkstraAlgorithmService.findShortestPath(coordinates));
    }

    @Autowired
    public void setDijkstraAlgorithmService(DijkstraAlgorithmService dijkstraAlgorithmService) {
        this.dijkstraAlgorithmService = dijkstraAlgorithmService;
    }
}
