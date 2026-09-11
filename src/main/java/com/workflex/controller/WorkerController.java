package com.workflex.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workers")
@PreAuthorize("hasRole('WORKER')")
public class WorkerController {

    @GetMapping("/profile")
    public String getProfile() {

        return "Worker Profile";
    }
}
