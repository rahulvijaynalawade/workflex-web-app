package com.workflex.attendance.controller;

import com.workflex.attendance.dto.AttendanceResponse;
import com.workflex.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@PreAuthorize("hasRole('WORKER')")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PutMapping("/{applicationId}/present")
    public ResponseEntity<AttendanceResponse> markAttendance(@PathVariable Long applicationId) {

        return ResponseEntity.ok(attendanceService.markAttendance(applicationId));
    }

    @PutMapping("/{applicationId}/absent")
    public ResponseEntity<AttendanceResponse> markAbsent(@PathVariable Long applicationId) {

        return ResponseEntity.ok(attendanceService.markAbsent(applicationId));
    }

    @PutMapping("/{applicationId}/complete")
    public ResponseEntity<AttendanceResponse> completeWork(@PathVariable Long applicationId) {

        return ResponseEntity.ok(attendanceService.completeWork(applicationId));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<AttendanceResponse> getMyAttendance(@PathVariable Long applicationId) {

        return ResponseEntity.ok(attendanceService.getMyAttendance(applicationId)
        );
    }
}

