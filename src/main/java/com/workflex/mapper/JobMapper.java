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

        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setSkills(job.getSkills());
        response.setLocation(job.getLocation());

        response.setPayment(job.getPayment());

        response.setWorkDate(job.getWorkDate());
        response.setStartTime(job.getStartTime());
        response.setEndTime(job.getEndTime());

        response.setRequiredWorkers(job.getRequiredWorkers());

        response.setFilledWorkers(job.getFilledWorkers());

        response.setAvailableWorkers(job.getRequiredWorkers() - job.getFilledWorkers());

        response.setEmployerId(job.getEmployer().getId());

        response.setEmployerName(job.getEmployer().getFullName());

        return response;
    }
}
