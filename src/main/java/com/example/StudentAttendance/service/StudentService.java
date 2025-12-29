package com.example.StudentAttendance.service;

import com.example.StudentAttendance.dto.AttendanceRequest;
import com.example.StudentAttendance.model.Attendance;
import com.example.StudentAttendance.model.Student;
import com.example.StudentAttendance.repository.AttendanceRepo;
import com.example.StudentAttendance.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {


    @Autowired
    StudentRepo studentRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;

    public List<Student> getAllStudents() {

        return studentRepo.findAll();
    }

    public void studentRegister(String name, int year, String department, String rollNo, String section) {
        if (rollNo == null || !rollNo.matches("^[A-Z0-9]{3,15}$")) {
            throw new IllegalArgumentException("Invalid roll number format");
        }
        rollNo = rollNo.toUpperCase();

        try{
            Student sr=new Student(name,year,department,rollNo,section);
            studentRepo.save(sr);
        } catch (DataIntegrityViolationException ex){
            throw new IllegalArgumentException("roll number already exists");
        }

    }




}
