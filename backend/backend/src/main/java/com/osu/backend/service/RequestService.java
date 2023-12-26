package com.osu.backend.service;

import com.osu.backend.dto.RequestDto;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.repository.CraneRepository;
import com.osu.backend.repository.RequestRepository;
import com.osu.backend.repository.ShipRepository;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final ShipRepository shipRepository;
    private final CraneRepository craneRepository;

//    private List<Ship> ships;



    List<Request> requests ;



//    @Transactional
//    public Request createRequest(RequestDto requestDto){
//
//        Ship ship = shipRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Ship is undefined"));
//        Request request = new Request();
//
////        Integer stayDay = ship.getPlannedStayDays();
//        LocalDateTime arrival = requestDto.getArrival();
//        LocalDateTime newArrival = requestDto.getNewArrival();
//
////        request.setArrival(arrival);
////        request.setDeparture(arrival.plusDays(stayDay));
//        request.setShip(ship);
//
//        return request;
//    }
//    public List<Ship> getShip(){
//
//        return shipRepository.findAll();
//    }



    public List<Ship> go(){
        List<Ship> ships = shipRepository.findAll();

        return ships;
    }

    public Request createRequest(RequestDto requestDto) {
        Ship ship = shipRepository.findById(requestDto.getId()).orElseThrow(() -> new RuntimeException("Ship is undefined"));
        Request request = new Request();

        List<Crane> cranes = craneRepository.findAll();

        LocalDateTime arrival = requestDto.getArrival();
        LocalDateTime new_arrival = requestDto.getNewArrival();
        int[] daysArrivalToAddOptions = {-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random1 = new Random();
        int randomIndexForArrival = random1.nextInt(daysArrivalToAddOptions.length -1 );
        int randomDaysToAddArrival = daysArrivalToAddOptions[randomIndexForArrival];

        Integer[] daysCraneToAddOptions = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Random random2 = new Random();
        Integer randomIndexForCrane = random2.nextInt(daysCraneToAddOptions.length -1 );
        Integer randomDaysToAddCrane = daysCraneToAddOptions[randomIndexForCrane];

        for (int i =  0 ; i < cranes.size()  ; i++) {
            if ("0".equals(cranes.get(i).getStatus()) && cranes.get(i).getCrane_type().getId() == ship.getShip_type().getId()) {
                cranes.get(i).setStatus("1");
                request.setCrane(cranes.get(i));
                request.setShip(ship);
                cranes.get(i).setWorkload(0);
                ship.setStatus("Работает");
                request.setDay_later(0L);

                request.setDay_of_stay(request.getShip().getValume() / request.getCrane().getCrane_type().getSpeed() ); // Планируемое время
                new_arrival = arrival.plusDays(randomDaysToAddArrival);
                LocalDateTime serving = new_arrival.plusDays(request.getDay_of_stay() +  randomDaysToAddCrane);
                request.setServing(serving);


                break;
            }
        }

        for (int i = 0; i < cranes.size()+1; i++) {
            if (cranes.get(i).getWorkload() > cranes.get(i+1).getWorkload() && cranes.get(i).getCrane_type().getId() == ship.getShip_type().getId()){
                cranes.get(i+1).incrementWorkload();
                request.setCrane(cranes.get(i+1));
                request.setShip(ship);
                request.setDay_of_stay(request.getShip().getValume()/request.getCrane().getCrane_type().getSpeed());
                break;
            } else if (cranes.get(i).getCrane_type().getId() == ship.getShip_type().getId()) {
                {
                    cranes.get(i).incrementWorkload();
                    request.setCrane(cranes.get(i));
                    request.setShip(ship);
                    request.setDay_of_stay(request.getShip().getValume()/request.getCrane().getCrane_type().getSpeed());
                    break;
                }
            }
        }

        request.setArrival_time(arrival);
        request.setNew_arrival_time(new_arrival);
        requestRepository.save(request);

        return request;
    }

public String deleteRequest(Long id){
    List<Request> requests = requestRepository.findAll();
    List<Crane> cranes = craneRepository.findAll();

    List<Request> newRequests = new ArrayList<>();

    requests.sort((r1, r2) -> Long.compare(r1.getCrane().getId(), r2.getCrane().getId()));


    for (int i = 0; i < requests.size() + 1; i++) {
        if (i < requests.size() && id.equals(requests.get(i).getId())) {

            for (Crane crane : cranes) {
                if (requests.get(i).getCrane().getId().equals(crane.getId())) {
                    crane.dencrementWorkload();
                    requests.get(i).getShip().setStatus("0");
                    requests.get(i).getCrane().setStatus("0");
                    requestRepository.deleteById(requests.get(i).getId());
                    return "Заявка с id " + id + " была удалена";
                }


            }

        }

    }

    requestRepository.deleteById(id);
    return "Заявка с id " + id + " не найдена";

}

public List<Request> workPort(){

    List<Request> requests = requestRepository.findAll();
    List<Ship> ships = shipRepository.findAll();
    requests.sort((r1, r2) -> Long.compare(r1.getCrane().getId(), r2.getCrane().getId()));

    for (Request request : requests){
        if(request.getCrane().getStatus().equals("1") && request.getShip().getStatus().equals("Работает")) {
            request.setStatus("Работает");
            request.setDay_later(0L);
            requestRepository.save(request);
        }
        else {
            request.setStatus("Ожидает");
            requestRepository.save(request);
        }
    }

    for (Request request : requests){

        LocalDateTime arrival = request.getArrival_time();
        LocalDateTime new_arrival = request.getNew_arrival_time();
        int[] daysToAddOptions = {0, 1, 2, 3};
        Random random = new Random();
        int randomIndex = random.nextInt(daysToAddOptions.length);
        int randomDaysToAdd = daysToAddOptions[randomIndex];

        if (request.getCrane().getStatus().equals("0")){
            for (Ship ship : ships){
                if(request.getShip().getId().equals(ship.getId())){
                    request.getCrane().setStatus("1");
                    request.getShip().setStatus("Работает");

                    new_arrival = arrival.plusDays(randomDaysToAdd);

                    LocalDateTime serving = new_arrival.plusDays(request.getDay_of_stay());
                    request.setServing(serving);

//                    request.setDay_later(0L);
                    request.setNew_arrival_time(new_arrival);
                    requestRepository.save(request);
                }
            }
        }
    }

    return requests;

    }

}