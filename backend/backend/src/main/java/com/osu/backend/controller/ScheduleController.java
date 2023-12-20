package com.osu.backend.controller;

import com.osu.backend.model.schedule.Schedule;
import com.osu.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;


    @PostMapping("/schedule")
    Schedule newSchedule(@RequestBody Schedule newSchedule) {
        return scheduleRepository.save(newSchedule);
    }

    @GetMapping("/schedules")
    List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

}
