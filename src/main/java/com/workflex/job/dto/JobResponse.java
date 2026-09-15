package com.workflex.job.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String skills;
    private String location;

    private Double payment;

    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer requiredWorkers;
    private Integer filledWorkers;
    private Long employerId;
    private String employerName;


    public JobResponse(){

    }

    public JobResponse(Long id,String title, String description, String skills, String location, Double payment, LocalDate workDate, LocalTime startTime, LocalTime endTime, Integer requiredWorkers, Integer filledWorkers, Long employerId, String employerName){
        this.id = id;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.payment = payment;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredWorkers = requiredWorkers;
        this.filledWorkers = filledWorkers;
        this.employerId = employerId;
        this.employerName = employerName;
        this.location = location;
    }


    public String getEmployerName() {
        return employerName;
    }



    public Long getEmployerId() {
        return employerId;
    }


    public Integer getFilledWorkers() {
        return filledWorkers;
    }


    public Integer getRequiredWorkers() {
        return requiredWorkers;
    }



    public LocalTime getEndTime() {
        return endTime;
    }



    public LocalTime getStartTime() {
        return startTime;
    }



    public LocalDate getWorkDate() {
        return workDate;
    }



    public Double getPayment() {
        return payment;
    }


    public String getLocation() {
        return location;
    }


    public String getSkills() {
        return skills;
    }



    public String getDescription() {
        return description;
    }



    public String getTitle() {
        return title;
    }



    public Long getId() {
        return id;
    }


}