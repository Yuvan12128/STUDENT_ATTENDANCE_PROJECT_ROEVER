package com.example.StudentAttendance.service;

import com.example.StudentAttendance.dto.AttendanceRequest;
import com.example.StudentAttendance.exception.AttendanceAlreadyMarkedException;
import com.example.StudentAttendance.model.Attendance;
import com.example.StudentAttendance.model.Student;
import com.example.StudentAttendance.repository.AttendanceRepo;
import com.example.StudentAttendance.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AttendanceService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;

    public void markAttendance(AttendanceRequest request) {
        //already DB la erukkura student datava yedukkurathukku
        Student student = studentRepo.findById(request.getStudentId()).orElseThrow(()->new RuntimeException("Student not found"));

        if(attendanceRepo.existsByStudentAndAttDate(student, LocalDate.now())){
            throw new AttendanceAlreadyMarkedException("Attendance already marked today");
        }

        Attendance att =new Attendance();
        att.setStudent(student);
        att.setStatus(request.getStatus());
        att.setMarkedBy(request.getMarkedBy());
        att.setAttDate(LocalDate.now());
        att.setAttTime(LocalDateTime.now());

        attendanceRepo.save(att);
    }
}
