package com.workflex.job.service;

import com.workflex.job.dto.JobRequest;
import com.workflex.job.dto.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobRequest request);

    List<JobResponse> getAllJobs();

    List<JobResponse> getMyJobs();

    JobResponse getMyJob(Long jobId);

    JobResponse updateJob(Long jobId, JobRequest request);

    void deleteJob(Long jobId);
}