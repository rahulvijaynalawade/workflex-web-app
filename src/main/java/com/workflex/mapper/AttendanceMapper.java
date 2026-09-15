package com.workflex.mapper;

import com.workflex.attendance.dto.AttendanceResponse;
import com.workflex.attendance.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceResponse toResponse(Attendance attendance) {

        AttendanceResponse response = new AttendanceResponse();

        response.setId(attendance.getId());

        response.setApplicationId(attendance.getApplication().getId());

        response.setWorkerId(attendance.getApplication().getWorker().getId());

        response.setWorkerName(attendance.getApplication().getWorker().getFullName());

        response.setJobId(attendance.getApplication().getJob().getId());

        response.setJobTitle(attendance.getApplication().getJob().getTitle());

        response.setWorkDate(attendance.getApplication().getJob().getWorkDate());

        response.setStartTime(attendance.getApplication().getJob().getStartTime());

        response.setEndTime(attendance.getApplication().getJob().getEndTime());

        response.setStatus(attendance.getStatus());

        response.setMarkedAt(attendance.getMarkedAt());

        response.setCompletedAt(attendance.getCompletedAt());

        return response;
    }
}