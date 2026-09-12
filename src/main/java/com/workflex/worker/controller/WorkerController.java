package com.workflex.worker.controller;

import com.workflex.worker.dto.WorkerProfileRequest;
import com.workflex.worker.dto.WorkerProfileResponse;
import com.workflex.worker.service.WorkerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
@PreAuthorize("hasRole('WORKER')")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/profile")
    public ResponseEntity<WorkerProfileResponse> createProfile(@Valid @RequestBody WorkerProfileRequest request) {

        WorkerProfileResponse response = workerService.createProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<WorkerProfileResponse> getProfile() {

        WorkerProfileResponse response = workerService.getProfile();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<WorkerProfileResponse> updateProfile(@Valid @RequestBody WorkerProfileRequest request) {

        WorkerProfileResponse response = workerService.updateProfile(request);

        return ResponseEntity.ok(response);
    }
}