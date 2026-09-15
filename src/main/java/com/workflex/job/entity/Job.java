package com.workflex.job.entity;

import com.workflex.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double payment;

    @Column(nullable = false)
    private LocalDate workDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer requiredWorkers;

    @Column(nullable = false)
    private Integer filledWorkers = 0;

    private Integer availableWorkers;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

}