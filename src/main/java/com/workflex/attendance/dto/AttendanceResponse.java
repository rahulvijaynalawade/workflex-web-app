package com.workflex.attendance.dto;

import com.workflex.enums.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {

    private Long id;

    private Long applicationId;

    private Long workerId;
    private String workerName;

    private Long jobId;
    private String jobTitle;

    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private AttendanceStatus status;

    private LocalDateTime markedAt;
    private LocalDateTime completedAt;
}