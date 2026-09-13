package com.workflex.worker.service;

import com.workflex.constant.MessageConstant;
import com.workflex.entity.User;
import com.workflex.exception.BadRequestException;
import com.workflex.exception.ResourceNotFoundException;
import com.workflex.mapper.WorkerProfileMapper;
import com.workflex.repository.UserRepository;
import com.workflex.worker.dto.WorkerProfileRequest;
import com.workflex.worker.dto.WorkerProfileResponse;
import com.workflex.worker.entity.WorkerProfile;
import com.workflex.worker.repository.WorkerProfileRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerProfileRepository workerProfileRepository;
    private final UserRepository userRepository;
    private final WorkerProfileMapper workerProfileMapper;

    public WorkerServiceImpl(WorkerProfileRepository workerProfileRepository, UserRepository userRepository, WorkerProfileMapper workerProfileMapper) {
        this.workerProfileRepository = workerProfileRepository;

        this.userRepository = userRepository;

        this.workerProfileMapper = workerProfileMapper;
    }

    @Override
    public WorkerProfileResponse createProfile(WorkerProfileRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));

        if (workerProfileRepository.findByUserId(user.getId()).isPresent()) {
            throw new ResourceNotFoundException(MessageConstant.WORKER_PROFILE_ALREADY_EXISTS);
        }

        WorkerProfile workerProfile = workerProfileMapper.toEntity(request);

        workerProfile.setUser(user);

        WorkerProfile savedProfile = workerProfileRepository.save(workerProfile);

        return workerProfileMapper.toResponse(savedProfile);
    }

    @Override
    public WorkerProfileResponse getProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));

        WorkerProfile workerProfile = workerProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.WORKER_PROFILE_NOT_FOUND));

        return workerProfileMapper.toResponse(workerProfile);
    }

    @Override
    public WorkerProfileResponse updateProfile(WorkerProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));

        WorkerProfile workerProfile = workerProfileRepository.findByUserId(user.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.WORKER_PROFILE_NOT_FOUND));

        workerProfile.setSkills(request.getSkills());

        workerProfile.setExperience(request.getExperience());

        workerProfile.setAvailability(request.getAvailability());

        WorkerProfile updatedProfile = workerProfileRepository.save(workerProfile);

        return workerProfileMapper.toResponse(updatedProfile);
    }
}