package com.osu.backend.service;

import com.osu.backend.model.request.Request;
import com.osu.backend.model.schedule.Schedule;
import com.osu.backend.repository.RequestRepository;
import com.osu.backend.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
//@NoArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final RequestRepository requestRepository;
    public List<Request> showSchedule() {
        List<Request> requests = requestRepository.findAll();

        return requests;
    }

}








