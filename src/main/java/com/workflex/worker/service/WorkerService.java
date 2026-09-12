package com.workflex.worker.service;

import com.workflex.worker.dto.WorkerProfileRequest;
import com.workflex.worker.dto.WorkerProfileResponse;

public interface WorkerService {

    WorkerProfileResponse createProfile(WorkerProfileRequest request);

    WorkerProfileResponse getProfile();

    WorkerProfileResponse updateProfile(WorkerProfileRequest request);
}