package com.osu.backend.controller;

import com.osu.backend.exception.ShipNotFoundException;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.service.ModelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class ModelingController {
    private final ModelingService modelingService;

    @Autowired
    public ModelingController(ModelingService modelingService) {
        this.modelingService = modelingService;
    }

    @PostMapping("/modeling/{step}")
    void modeling(@PathVariable int step) {
        modelingService.startModeling(step);
    }
}
