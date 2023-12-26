package com.osu.backend.service;

import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.schedule.Schedule;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.repository.RequestRepository;
import com.osu.backend.repository.ScheduleRepository;
import com.osu.backend.repository.ShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PortService {

    private final RequestRepository requestRepository;
    private final ScheduleRepository scheduleRepository;
    private final ShipRepository shipRepository;

    private List<Request> requests ;
    private List<Crane> cranes;

    public List<Request> showPort() {
        List<Request> requests = requestRepository.findAll();

        return requests;
    }

    public void work(int step){
        List<Request> requests = requestRepository.findAll();
        List<Ship> ships = shipRepository.findAll();
        requests.sort((r1, r2) -> Long.compare(r1.getCrane().getId(), r2.getCrane().getId()));

        LocalDateTime arrival = LocalDateTime.now();

        for (Request request : requests){
            if(request.getStatus().equals("Работает")){
                if(request.getShip().getValume() > 0){
                    Integer volume = request.getShip().getValume() ;
                    Integer newVolume = volume - request.getCrane().getCrane_type().getSpeed() * step;
                    request.getShip().setValume(newVolume);
                    Long duration = ChronoUnit.DAYS.between(request.getArrival_time(), request.getNew_arrival_time());
                    request.setDay_later(duration);
                    requestRepository.save(request);
                }

                if(request.getShip().getValume() <= 0){
                    request.getShip().setStatus("0");
                    request.getCrane().setStatus("0");
                    request.getShip().setValume(0);
                    request.setStatus("Разгружен");
                    arrival = request.getServing();

                    requestRepository.save(request);

                }
            }
        }

        for (Request request : requests){

//            LocalDateTime arrival = request.getTemp();
            LocalDateTime new_arrival = request.getNew_arrival_time();
            int[] daysToAddOptions = {0, 1, 2, 3};
            Random random = new Random();
            int randomIndex = random.nextInt(daysToAddOptions.length);
            int randomDaysToAdd = daysToAddOptions[randomIndex];

            if (request.getCrane().getStatus().equals("0") && !request.getStatus().equals("Разгружен")){
                for (Ship ship : ships){
                    if(request.getShip().getId().equals(ship.getId()) ){
                        request.getCrane().setStatus("1");
                        request.getShip().setStatus("Работает");
                        request.setStatus("Работает");
                        new_arrival = arrival.plusDays(randomDaysToAdd);

                        LocalDateTime serving = new_arrival.plusDays(request.getDay_of_stay());
                        request.setServing(serving);

                        request.setNew_arrival_time(new_arrival);
                        requestRepository.save(request);
                    }
                }
            }
        }

//      for (Request request : requests){
//          for (Crane crane : cranes){
//              if(request.getStatus().equals("Работает") && request.getCrane().getName() == crane.getName()){
//                    request.setDay_later(0L);
//                    requestRepository.save(request);
//                    break;
//              }
//          }
//      }

    }
}
