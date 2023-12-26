package com.osu.backend.controller;

import com.osu.backend.model.request.Request;
import com.osu.backend.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class PortController {

    @Autowired
    private PortService portService;

    @PostMapping("/work")
    void newRequest(){
        portService.work(1);
    }

    @GetMapping("/result")
    List<Request> getAllRequest(){
        return portService.showPort();
    }
}
