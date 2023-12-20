package com.osu.backend.repository;

import com.osu.backend.model.cargo.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
