package com.osu.backend.repository;

import com.osu.backend.model.ship.Ship;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE ship AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Ship")
    void deleteAllAndResetIds();
}
