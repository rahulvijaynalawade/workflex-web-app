package com.workflex.job.service;

import com.workflex.constant.MessageConstant;
import com.workflex.user.entity.User;
import com.workflex.exception.ForbiddenException;
import com.workflex.exception.ResourceNotFoundException;
import com.workflex.job.dto.JobRequest;
import com.workflex.job.dto.JobResponse;
import com.workflex.job.entity.Job;
import com.workflex.job.repository.JobRepository;
import com.workflex.mapper.JobMapper;

import com.workflex.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<JobResponse> getMyJobs() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User employer = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));

        List<Job> jobs = jobRepository.findByEmployerId(employer.getId());

        return jobs.stream().map(jobMapper::toResponse).toList();
    }

    @Override
    public JobResponse createJob(JobRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User employer = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));

        Job job = jobMapper.toEntity(request);

        job.setEmployer(employer);

        Job savedJob = jobRepository.save(job);

        return jobMapper.toResponse(savedJob);
    }

    @Override
    public List<JobResponse> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(jobMapper::toResponse).toList();
    }

    private User getCurrentEmployer() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));
    }

    private void validateOwnership(Job job, User employer) {

        if (!job.getEmployer().getId().equals(employer.getId())) {

            throw new ForbiddenException(MessageConstant.JOB_ACCESS_FORBIDDEN);
        }
    }

    @Override
    public JobResponse getMyJob(Long jobId) {
        User employer = getCurrentEmployer();

        Job job = jobRepository.findById(jobId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.JOB_NOT_FOUND));

        validateOwnership(job, employer);

        return jobMapper.toResponse(job);
    }

    @Override
    public JobResponse updateJob(Long jobId, JobRequest request) {

        User employer = getCurrentEmployer();

        Job job = jobRepository.findById(jobId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.JOB_NOT_FOUND));

        validateOwnership(job, employer);

        job.setTitle(request.getTitle());

        job.setDescription(request.getDescription());

        job.setSkills(request.getSkills());

        job.setLocation(request.getLocation());

        job.setPayment(request.getPayment());

        Job updatedJob = jobRepository.save(job);

        return jobMapper.toResponse(updatedJob);
    }

    @Override
    public void deleteJob(Long jobId) {

        User employer = getCurrentEmployer();

        Job job = jobRepository.findById(jobId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.JOB_NOT_FOUND));

        validateOwnership(job, employer);

        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> searchJobs(String keyword) {

        List<Job> jobs = jobRepository.searchJobs(keyword);

        return jobs.stream().map(jobMapper::toResponse).toList();
    }
}