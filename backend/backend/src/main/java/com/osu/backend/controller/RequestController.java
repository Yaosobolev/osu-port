package com.osu.backend.controller;

//import com.osu.backend.dto.RequestDto;
import com.osu.backend.dto.RequestDto;
import com.osu.backend.exception.RequestNotFoundException;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.repository.RequestRepository;
//import com.osu.backend.service.RequestService;
import com.osu.backend.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;


    @Autowired
    private RequestService requestService;

//   private Ship ship;



//    @PostMapping("/request")
//    Request newRequest(@RequestBody Request newRequest){
//        return requestRepository.save(newRequest);
//    }

    @PostMapping("/request")
    Request newRequest(@RequestBody RequestDto newRequest){
        return requestService.createRequest(newRequest);
    }

    @GetMapping("/requests")
    List<Request> getAllRequest(){
        return requestService.workPort();
    }
//    @GetMapping("/requests")
//    List<Ship> getAllRequest(){
//        return requestService.go();
//    }
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
                    request.setDay_of_stay(newRequest.getDay_of_stay());
                    request.setShip(newRequest.getShip());
                    request.setCrane(newRequest.getCrane());
                    request.setStatus(newRequest.getStatus());

                    return requestRepository.save(request);
                }).orElseThrow(()->new RequestNotFoundException(id));
    }

    @DeleteMapping("/request/{id}")
    String deleteRequest(@PathVariable Long id){

        return requestService.deleteRequest(id);
    }

//    @DeleteMapping("/request/{id}")
//    String deleteRequest(@PathVariable Long id){
//        if(!requestRepository.existsById(id)){
//            throw new RequestNotFoundException(id);
//        }
//        requestRepository.deleteById(id);
//        return "Заявка с id " + id + " был удалена";
//    }
}

//@RestController
//@RequestMapping(path="request")
////@RequiredArgsConstructor
//@AllArgsConstructor
//public class RequestController {
//    private final RequestService requestService;
//    private final PortService portService;
//
//    @PostMapping("/create")
//    public ResponseEntity<Request> create(@RequestBody RequestDto requestDto) {
//        Request request = requestService.create(requestDto);
//
//        return new ResponseEntity<>(request, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Request> update(@RequestBody RequestDto requestDto) {
//        Request request = requestService.update(requestDto.getId(), requestDto);
////        portService.work();
//        return new ResponseEntity<>(request, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> delete(@RequestBody RequestDto shipDto) {
//        boolean isDeleted = requestService.deleteRequest(shipDto.getId());
//
//        if (isDeleted) {
////            portService.work();
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}