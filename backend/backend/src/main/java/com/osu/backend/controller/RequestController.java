package com.osu.backend.controller;

import com.osu.backend.exception.RequestNotFoundException;
import com.osu.backend.model.request.Request;
import com.osu.backend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;


    @PostMapping("/request")
    Request newRequest(@RequestBody Request newRequest){
        return requestRepository.save(newRequest);
    }

    @GetMapping("/requests")
    List<Request> getAllRequest(){
        return requestRepository.findAll();
    }

    @GetMapping("/request/{id}")
    Request getRequestById(@PathVariable Long id) {
        return requestRepository.findById(id)
                .orElseThrow(()-> new RequestNotFoundException(id));
    }

    @PutMapping("/request/{id}")
    Request updateRequest(@RequestBody Request newRequest, @PathVariable Long id){
        return requestRepository.findById(id)
                .map(request -> {
                    request.setArrival_time(newRequest.getArrival_time());
                    request.setShip_id(newRequest.getShip_id());
                    request.setCargo_id(newRequest.getCargo_id());
                    return requestRepository.save(request);
                }).orElseThrow(()->new RequestNotFoundException(id));
    }

    @DeleteMapping("/request/{id}")
    String deleteRequest(@PathVariable Long id){
        if(!requestRepository.existsById(id)){
            throw new RequestNotFoundException(id);
        }
        requestRepository.deleteById(id);
        return "Заявка с id " + id + " был удалена";
    }
}