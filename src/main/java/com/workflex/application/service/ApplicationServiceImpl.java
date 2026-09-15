package com.workflex.application.service;

import com.workflex.application.dto.ApplicationResponse;
import com.workflex.application.entity.Application;
import com.workflex.enums.ApplicationStatus;
import com.workflex.application.repository.ApplicationRepository;
import com.workflex.entity.User;
import com.workflex.exception.BadRequestException;
import com.workflex.exception.ResourceNotFoundException;
import com.workflex.job.entity.Job;
import com.workflex.job.repository.JobRepository;
import com.workflex.mapper.ApplicationMapper;
import com.workflex.constant.MessageConstant;
import com.workflex.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository, ApplicationMapper applicationMapper) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationResponse applyForJob(Long jobId) {

        User worker = getCurrentUser();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.JOB_NOT_FOUND));

        // Worker cannot apply to own job
        if (job.getEmployer().getId().equals(worker.getId())) {

            throw new BadRequestException(MessageConstant.CANNOT_APPLY_OWN_JOB);
        }

        // Check whether job is already full
        if (job.getFilledWorkers() >= job.getRequiredWorkers()) {

            throw new BadRequestException(MessageConstant.JOB_ALREADY_FULL);
        }

        // Check duplicate application
        if (applicationRepository.findByWorkerIdAndJobId(worker.getId(), jobId).isPresent()) {

            throw new BadRequestException(MessageConstant.ALREADY_APPLIED);
        }

        Application application = new Application();

        application.setWorker(worker);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);
        application.setAppliedAt(LocalDateTime.now());

        Application savedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }

    @Override
    public List<ApplicationResponse> getMyApplications() {

        User worker = getCurrentUser();

        return applicationRepository.findByWorkerId(worker.getId()).stream().map(applicationMapper::toResponse).toList();
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));
    }
}
