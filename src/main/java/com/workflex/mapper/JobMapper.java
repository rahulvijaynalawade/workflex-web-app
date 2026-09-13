package com.workflex.mapper;

import com.workflex.job.dto.JobRequest;
import com.workflex.job.dto.JobResponse;
import com.workflex.job.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public Job toEntity(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());

        return job;
    }

    public JobResponse toResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkills(),
                job.getLocation(),
                job.getSalary(),
                job.getEmployer().getId(),
                job.getEmployer().getFullName()
        );
    }
}
