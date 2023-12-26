package com.osu.backend.repository;

import com.osu.backend.model.crane.Crane;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CraneRepository extends JpaRepository<Crane, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Crane SET status = '0' ")
    void clearCraneStatus();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Crane SET workload = 0")
    void clearCraneWorkload();

}
