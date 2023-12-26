package com.osu.backend.repository;

import com.osu.backend.model.request.Request;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE request AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Request ")
    void deleteAllAndResetIds();

}
