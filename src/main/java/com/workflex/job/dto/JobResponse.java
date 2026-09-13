package com.workflex.job.dto;

public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String skills;
    private String location;
    private Double salary;
    private Long employerId;
    private String employerName;

    public JobResponse() {
    }

    public JobResponse(Long id, String title, String description, String skills, String location, Double salary, Long employerId, String employerName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
        this.salary = salary;
        this.employerId = employerId;
        this.employerName = employerName;
    }

    public Long getId() {
        return id;
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

    public Double getSalary() {
        return salary;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public String getEmployerName() {
        return employerName;
    }
}