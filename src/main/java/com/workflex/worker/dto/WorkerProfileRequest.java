package com.workflex.worker.dto;

import jakarta.validation.constraints.NotBlank;

public class WorkerProfileRequest {

    @NotBlank(message = "Skills are required")
    private String skills;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Availability is required")
    private String availability;

    public WorkerProfileRequest() {
    }

    public WorkerProfileRequest(
            String skills,
            String experience,
            String availability) {

        this.skills = skills;
        this.experience = experience;
        this.availability = availability;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}