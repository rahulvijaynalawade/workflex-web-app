package com.workflex.application.dto;

import com.workflex.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {

    private Long id;

    private Long workerId;
    private String workerName;

    private Long jobId;
    private String jobTitle;

    private String location;

    private Double payment;

    private java.time.LocalDate workDate;
    private java.time.LocalTime startTime;
    private java.time.LocalTime endTime;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
