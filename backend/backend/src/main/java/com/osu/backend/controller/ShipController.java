package com.osu.backend.controller;

import com.osu.backend.exception.ShipNotFoundException;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ShipController {
    @Autowired
    private ShipRepository shipRepository;


    @PostMapping("/ship")
    Ship newShip(@RequestBody Ship newShip){
        return shipRepository.save(newShip);
    }

    @GetMapping("/ships")
    List<Ship> getAllShip(){
        return shipRepository.findAll();
    }

    @GetMapping("/ship/{id}")
    Ship getShipById(@PathVariable Long id) {
        return shipRepository.findById(id)
                .orElseThrow(()-> new ShipNotFoundException(id));
    }

    @PutMapping("/ship/{id}")
    Ship updateShip(@RequestBody Ship newShip, @PathVariable Long id){
        return shipRepository.findById(id)
                .map(ship -> {
                    ship.setName(newShip.getName());
                    ship.setWeight(newShip.getWeight());
                    ship.setDay_of_stay(newShip.getDay_of_stay());
                    ship.setShip_type(newShip.getShip_type());
                    return shipRepository.save(ship);
                }).orElseThrow(()->new ShipNotFoundException(id));
    }

    @DeleteMapping("/ship/{id}")
    String deleteShip(@PathVariable Long id){
        if(!shipRepository.existsById(id)){
            throw new ShipNotFoundException(id);
        }
        shipRepository.deleteById(id);
        return "Судно с id " + id + " был удален";
    }
}
