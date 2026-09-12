package com.workflex.worker.entity;

import com.workflex.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "worker_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    private String experience;

    @Column(nullable = false)
    private String availability;

    private Double rating;
}