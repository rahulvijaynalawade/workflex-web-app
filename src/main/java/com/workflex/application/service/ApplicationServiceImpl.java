package com.workflex.application.service;

import com.workflex.application.dto.ApplicationResponse;
import com.workflex.application.entity.Application;
import com.workflex.attendance.entity.Attendance;
import com.workflex.attendance.repository.AttendanceRepository;
import com.workflex.enums.ApplicationStatus;
import com.workflex.application.repository.ApplicationRepository;
import com.workflex.user.entity.User;
import com.workflex.enums.AttendanceStatus;
import com.workflex.exception.BadRequestException;
import com.workflex.exception.ForbiddenException;
import com.workflex.exception.ResourceNotFoundException;
import com.workflex.job.entity.Job;
import com.workflex.job.repository.JobRepository;
import com.workflex.mapper.ApplicationMapper;
import com.workflex.constant.MessageConstant;
import com.workflex.user.repository.UserRepository;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final AttendanceRepository attendanceRepository;
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(AttendanceRepository attendanceRepository, ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository, ApplicationMapper applicationMapper) {
        this.attendanceRepository = attendanceRepository;

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

    @Override
    public List<ApplicationResponse> getJobApplications(Long jobId) {

        User employer = getCurrentUser();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.JOB_NOT_FOUND));

        validateEmployerOwnership(job, employer);

        return applicationRepository.findByJobId(jobId).stream().map(applicationMapper::toResponse).toList();
    }

    private void validateEmployerOwnership(Job job, User employer) {

        if (!job.getEmployer().getId().equals(employer.getId())) {

            throw new ForbiddenException(MessageConstant.APPLICATION_ACCESS_FORBIDDEN);
        }
    }

    @Override
    public ApplicationResponse acceptApplication(Long applicationId) {

        User employer = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        Job job = application.getJob();

        validateEmployerOwnership(job, employer);

        if (application.getStatus() != ApplicationStatus.PENDING) {

            throw new BadRequestException(MessageConstant.APPLICATION_NOT_PENDING);
        }

        if (job.getFilledWorkers() >= job.getRequiredWorkers()) {

            throw new BadRequestException(MessageConstant.JOB_FULL);
        }

        application.setStatus(ApplicationStatus.ACCEPTED);

        job.setFilledWorkers(job.getFilledWorkers() + 1);



        applicationRepository.save(application);
        jobRepository.save(job);

        Attendance attendance = new Attendance();

        attendance.setApplication(application);
        attendance.setStatus(AttendanceStatus.PENDING);
        attendanceRepository.save(attendance);

        return applicationMapper.toResponse(application);
    }

    @Override
    public ApplicationResponse rejectApplication(Long applicationId) {

        User employer = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        Job job = application.getJob();

        validateEmployerOwnership(job, employer);

        if (application.getStatus() != ApplicationStatus.PENDING) {

            throw new BadRequestException(MessageConstant.APPLICATION_NOT_PENDING);
        }

        application.setStatus(ApplicationStatus.REJECTED);

        Application savedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }
}
