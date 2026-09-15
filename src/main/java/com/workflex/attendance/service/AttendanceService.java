package com.workflex.attendance.service;

import com.workflex.attendance.dto.AttendanceResponse;

public interface AttendanceService {

    AttendanceResponse markAttendance(Long applicationId);

    AttendanceResponse markAbsent(Long applicationId);

    AttendanceResponse completeWork(Long applicationId);

    AttendanceResponse getMyAttendance(Long applicationId);
}
