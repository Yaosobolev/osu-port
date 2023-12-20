package com.osu.backend.controller;


import com.osu.backend.exception.CargoNotFoundException;
import com.osu.backend.model.cargo.Cargo;
import com.osu.backend.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;


    @PostMapping("/cargo")
    Cargo newCrane(@RequestBody Cargo newCargo) {
        return cargoRepository.save(newCargo);
    }

    @GetMapping("/cargos")
    List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    @GetMapping("/cargo/{id}")
    Cargo getCargoById(@PathVariable Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(id));
    }

    @PutMapping("/cargo/{id}")
    Cargo updateCargo(@RequestBody Cargo newCargo, @PathVariable Long id) {
        return cargoRepository.findById(id)
                .map(cargo -> {
                    cargo.setName(newCargo.getName());
                    cargo.setValue(newCargo.getValue());
                    cargo.setCargo_type(newCargo.getCargo_type());
                    return cargoRepository.save(cargo);
                }).orElseThrow(() -> new CargoNotFoundException(id));
    }

    @DeleteMapping("/cargo/{id}")
    String deleteCargo(@PathVariable Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new CargoNotFoundException(id);
        }
        cargoRepository.deleteById(id);
        return "Груз с id " + id + " был удален";
    }
}
