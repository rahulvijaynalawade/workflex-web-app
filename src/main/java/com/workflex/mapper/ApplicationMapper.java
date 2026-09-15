package com.workflex.mapper;

import com.workflex.application.dto.ApplicationResponse;
import com.workflex.application.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponse toResponse(Application application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());

        response.setWorkerId(application.getWorker().getId());

        response.setWorkerName(application.getWorker().getFullName());

        response.setJobId(application.getJob().getId());

        response.setJobTitle(application.getJob().getTitle());

        response.setLocation(application.getJob().getLocation());

        response.setPayment(application.getJob().getPayment());

        response.setWorkDate(application.getJob().getWorkDate());

        response.setStartTime(application.getJob().getStartTime());

        response.setEndTime(application.getJob().getEndTime());

        response.setStatus(application.getStatus());

        response.setAppliedAt(application.getAppliedAt());

        return response;
    }
}
