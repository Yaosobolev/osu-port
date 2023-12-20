package com.osu.backend.repository;

import com.osu.backend.model.ship.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}
