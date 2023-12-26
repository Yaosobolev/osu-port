package com.osu.backend.service;

import com.osu.backend.model.ship.Ship;
import com.osu.backend.repository.ShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;
    public List<Ship> showShips(){
        List<Ship> ships = shipRepository.findAll();
        for (Ship ship : ships){
            ship.setStatus("0");
            shipRepository.save(ship);
        }
        List<Ship> s = shipRepository.findAll();
        return s;
    }
}
