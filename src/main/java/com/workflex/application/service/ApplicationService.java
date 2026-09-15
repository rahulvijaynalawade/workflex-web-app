package com.workflex.application.service;

import com.workflex.application.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse applyForJob(Long jobId);

    List<ApplicationResponse> getMyApplications();
}