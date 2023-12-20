package com.osu.backend.repository;

import com.osu.backend.model.crane.Crane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CraneRepository extends JpaRepository<Crane, Long> {
}
