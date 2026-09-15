
package com.workflex.attendance.service;

import com.workflex.application.entity.Application;
import com.workflex.enums.ApplicationStatus;
import com.workflex.application.repository.ApplicationRepository;
import com.workflex.attendance.dto.AttendanceResponse;
import com.workflex.attendance.entity.Attendance;
import com.workflex.enums.AttendanceStatus;
import com.workflex.attendance.repository.AttendanceRepository;
import com.workflex.constant.MessageConstant;
import com.workflex.user.entity.User;
import com.workflex.exception.BadRequestException;
import com.workflex.exception.ForbiddenException;
import com.workflex.exception.ResourceNotFoundException;
import com.workflex.mapper.AttendanceMapper;
import com.workflex.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ApplicationRepository applicationRepository, UserRepository userRepository, AttendanceMapper attendanceMapper) {

        this.attendanceRepository = attendanceRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public AttendanceResponse markAttendance(Long applicationId) {

        User worker = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        validateWorker(application, worker);

        if (application.getStatus() != ApplicationStatus.ACCEPTED) {

            throw new BadRequestException(MessageConstant.WORKER_NOT_ASSIGNED);
        }

        Attendance attendance = getAttendance(applicationId);

        if (attendance.getStatus() != AttendanceStatus.PENDING) {

            throw new BadRequestException(MessageConstant.ATTENDANCE_ALREADY_MARKED);
        }

        attendance.setStatus(AttendanceStatus.PRESENT);

        attendance.setMarkedAt(LocalDateTime.now());

        Attendance saved = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(saved);
    }

    @Override
    public AttendanceResponse markAbsent(Long applicationId) {

        User worker = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        validateWorker(application, worker);

        if (application.getStatus() != ApplicationStatus.ACCEPTED) {

            throw new BadRequestException(MessageConstant.WORKER_NOT_ASSIGNED);
        }

        Attendance attendance = getAttendance(applicationId);

        if (attendance.getStatus() != AttendanceStatus.PENDING) {

            throw new BadRequestException(MessageConstant.ATTENDANCE_ALREADY_MARKED);
        }

        attendance.setStatus(AttendanceStatus.ABSENT);

        attendance.setMarkedAt(LocalDateTime.now());

        Attendance saved = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(saved);
    }

    @Override
    public AttendanceResponse completeWork(Long applicationId) {

        User worker = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        validateWorker(application, worker);

        Attendance attendance = getAttendance(applicationId);

        if (attendance.getStatus() != AttendanceStatus.PRESENT) {

            throw new BadRequestException(MessageConstant.WORK_NOT_COMPLETED);
        }

        attendance.setStatus(AttendanceStatus.COMPLETED);

        attendance.setCompletedAt(LocalDateTime.now());

        Attendance saved = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(saved);
    }

    @Override
    public AttendanceResponse getMyAttendance(Long applicationId) {

        User worker = getCurrentUser();

        Application application = applicationRepository.findById(applicationId)
                        .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.APPLICATION_NOT_FOUND));

        validateWorker(application, worker);

        Attendance attendance = getAttendance(applicationId);

        return attendanceMapper.toResponse(attendance);
    }

    private Attendance getAttendance(Long applicationId) {

        return attendanceRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.ATTENDANCE_NOT_FOUND));
    }

    private void validateWorker(Application application, User worker) {

        if (!application.getWorker().getId().equals(worker.getId())) {
            throw new ForbiddenException(MessageConstant.ATTENDANCE_ACCESS_FORBIDDEN);
        }
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND));
    }
}