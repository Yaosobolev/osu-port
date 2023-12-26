package com.osu.backend.controller;

import com.osu.backend.model.request.Request;
import com.osu.backend.model.schedule.Schedule;
import com.osu.backend.repository.ScheduleRepository;
//import com.osu.backend.service.ScheduleService;
//import com.osu.backend.service.ScheduleService;
import com.osu.backend.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedules")
    List<Request> getAllSchedule() {
        return scheduleService.showSchedule();
    }


}
