package com.workflex.mapper;

import com.workflex.worker.dto.WorkerProfileRequest;
import com.workflex.worker.dto.WorkerProfileResponse;
import com.workflex.worker.entity.WorkerProfile;
import org.springframework.stereotype.Component;

@Component
public class WorkerProfileMapper {

    public WorkerProfile toEntity(WorkerProfileRequest request) {

        WorkerProfile workerProfile = new WorkerProfile();

        workerProfile.setSkills(request.getSkills());

        workerProfile.setExperience(request.getExperience());

        workerProfile.setAvailability(request.getAvailability());

        return workerProfile;
    }

    public WorkerProfileResponse toResponse(WorkerProfile workerProfile) {

        return new WorkerProfileResponse(
                workerProfile.getId(),
                workerProfile.getUser().getId(),
                workerProfile.getUser().getFullName(),
                workerProfile.getSkills(),
                workerProfile.getExperience(),
                workerProfile.getAvailability(),
                workerProfile.getRating()
        );
    }
}
