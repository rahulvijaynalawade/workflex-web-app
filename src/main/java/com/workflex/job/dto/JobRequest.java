package com.workflex.job.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotBlank(message = "Skills are required")
    private String skills;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Payment is required")
    @Positive(message = "Payment must be greater than zero")
    private Double payment;

    @NotNull(message = "Work date is required")
    private LocalDate workDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Required workers is required")
    @Positive(message = "Required workers must be greater than zero")
    private Integer requiredWorkers;

    public JobRequest() {
    }

    public JobRequest(
            String title,
            String description,
            String skills,
            String location,
            Double payment,
            LocalDate workDate,
            LocalTime startTime,
            LocalTime endTime,
            Integer requiredWorkers) {

        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
        this.payment = payment;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredWorkers = requiredWorkers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

    public String getLocation() {
        return location;
    }

    public Double getPayment() {
        return payment;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Integer getRequiredWorkers() {
        return requiredWorkers;
    }

    // getters and setters
}