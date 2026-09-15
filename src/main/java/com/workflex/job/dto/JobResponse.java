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
    private Integer availableWorkers;

    private Long employerId;
    private String employerName;



    public JobResponse(Long id, String title, String description, String skills, String location, Double payment, LocalDate workDate, LocalTime startTime, LocalTime endTime, Integer requiredWorkers, Integer filledWorkers, Integer availableWorkers, Long employerId, String employerName){
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
        this.availableWorkers = availableWorkers;
        this.employerId = employerId;
        this.employerName = employerName;
        this.location = location;
    }

    public JobResponse() {

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

    public Integer getAvailableWorkers(){
        return availableWorkers;
    }

    public Long setId(Long id) {
        return id;
    }

    public String setTitle(String title) {
        return title;
    }

    public String setDescription(String description) {
        return description;
    }

    public String setSkills(String skills) {
        return skills;
    }

    public String setLocation(String location) {
        return location;
    }

    public Double setPayment(Double payment) {
        return payment;
    }

    public LocalDate setWorkDate(LocalDate workDate) {
        return workDate;
    }

    public LocalTime setStartTime(LocalTime startTime) {
        return startTime;
    }

    public LocalTime setEndTime(LocalTime endTime) {
        return endTime;
    }

    public Integer setRequiredWorkers(Integer requiredWorkers) {
        return requiredWorkers;
    }

    public Integer setFilledWorkers(Integer filledWorkers) {
        return filledWorkers;
    }

    public Integer setAvailableWorkers(int i) {
        return availableWorkers;
    }

    public Long setEmployerId(Long id) {
        return employerId;
    }

    public String setEmployerName(String fullName) {
        return employerName;
    }
}