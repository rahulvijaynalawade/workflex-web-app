package com.workflex.job.repository;

import com.workflex.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployerId(Long employerId);

    @Query("""
        SELECT j FROM Job j
        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.skills) LIKE LOWER(CONCAT('%', :keyword, '%'))
        """)
    List<Job> searchJobs(@Param("keyword") String keyword);
}