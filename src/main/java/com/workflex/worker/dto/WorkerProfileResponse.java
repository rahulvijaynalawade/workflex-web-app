package com.workflex.worker.dto;

public class WorkerProfileResponse {

    private Long id;
    private Long userId;
    private String fullName;
    private String skills;
    private String experience;
    private String availability;
    private Double rating;

    public WorkerProfileResponse() {
    }

    public WorkerProfileResponse(
            Long id,
            Long userId,
            String fullName,
            String skills,
            String experience,
            String availability,
            Double rating) {

        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.skills = skills;
        this.experience = experience;
        this.availability = availability;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSkills() {
        return skills;
    }

    public String getExperience() {
        return experience;
    }

    public String getAvailability() {
        return availability;
    }

    public Double getRating() {
        return rating;
    }
}