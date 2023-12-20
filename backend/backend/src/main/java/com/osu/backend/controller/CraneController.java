package com.osu.backend.controller;

import com.osu.backend.exception.CraneNotFoundException;
import com.osu.backend.model.crane.Crane;
import com.osu.backend.repository.CraneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class CraneController {
    @Autowired
    private CraneRepository craneRepository;


    @PostMapping("/crane")
    Crane newCrane(@RequestBody Crane newCrane){
        return craneRepository.save(newCrane);
    }

    @GetMapping("/cranes")
    List<Crane> getAllCranes(){
        return craneRepository.findAll();
    }

  @GetMapping("/crane/{id}")
   Crane getCraneById(@PathVariable Long id) {
       return craneRepository.findById(id)
              .orElseThrow(()-> new CraneNotFoundException(id));
   }

  @PutMapping("/crane/{id}")
   Crane updateCrane(@RequestBody Crane newCrane, @PathVariable Long id){
      return craneRepository.findById(id)
              .map(crane -> {
                 crane.setName(newCrane.getName());
                  crane.setCrane_type(newCrane.getCrane_type());
                    return craneRepository.save(crane);
               }).orElseThrow(()->new CraneNotFoundException(id));
   }

   @DeleteMapping("/crane/{id}")
   String deleteCrane(@PathVariable Long id){
        if(!craneRepository.existsById(id)){
           throw new CraneNotFoundException(id);
       }
       craneRepository.deleteById(id);
       return "Кран с id " + id + " был удален";
  }
}
