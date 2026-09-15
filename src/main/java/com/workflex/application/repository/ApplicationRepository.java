package com.workflex.application.repository;

import com.workflex.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByWorkerIdAndJobId(Long workerId, Long jobId);

    List<Application> findByWorkerId(Long workerId);

    List<Application> findByJobId(Long jobId);
}
