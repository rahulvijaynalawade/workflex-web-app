package com.workflex.job.entity;

import com.workflex.entity.User;
import jakarta.persistence.*;
import lombok.*;

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
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;
}
