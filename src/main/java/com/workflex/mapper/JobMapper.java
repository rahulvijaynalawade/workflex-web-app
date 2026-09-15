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

        job.setPayment(request.getPayment());

        job.setWorkDate(request.getWorkDate());

        job.setStartTime(request.getStartTime());

        job.setEndTime(request.getEndTime());

        job.setRequiredWorkers(request.getRequiredWorkers());

        job.setFilledWorkers(0);

        return job;
    }

    public JobResponse toResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkills(),
                job.getLocation(),
                job.getPayment(),
                job.getWorkDate(),
                job.getStartTime(),
                job.getEndTime(),
                job.getRequiredWorkers(),
                job.getFilledWorkers(),
                job.getEmployer().getId(),
                job.getEmployer().getFullName()
        );
    }
}
