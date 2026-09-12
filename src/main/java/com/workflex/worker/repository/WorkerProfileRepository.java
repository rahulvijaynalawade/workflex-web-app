package com.workflex.worker.repository;

import com.workflex.worker.entity.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerProfileRepository
        extends JpaRepository<WorkerProfile, Long> {

    Optional<WorkerProfile> findByUserId(Long userId);
}