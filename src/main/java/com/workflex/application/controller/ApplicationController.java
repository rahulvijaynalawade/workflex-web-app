package com.workflex.application.controller;

import com.workflex.application.dto.ApplicationResponse;
import com.workflex.application.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@PreAuthorize("hasRole('WORKER')")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{jobId}")
    public ResponseEntity<ApplicationResponse> applyForJob(@PathVariable Long jobId) {

        ApplicationResponse response = applicationService.applyForJob(jobId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/my-applications")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications() {

        return ResponseEntity.ok(applicationService.getMyApplications());
    }
}
