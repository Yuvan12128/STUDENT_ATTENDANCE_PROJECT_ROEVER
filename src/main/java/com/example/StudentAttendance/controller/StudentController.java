package com.example.StudentAttendance.controller;

import com.example.StudentAttendance.dto.AttendanceRequest;
import com.example.StudentAttendance.exception.AttendanceAlreadyMarkedException;
import com.example.StudentAttendance.model.Attendance;
import com.example.StudentAttendance.model.Student;

import com.example.StudentAttendance.repository.AttendanceRepo;
import com.example.StudentAttendance.service.AttendanceService;
import com.example.StudentAttendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("student/register")
    public String studentRegister(@RequestParam("name") String name,
                                  @RequestParam("year") int year,
                                  @RequestParam("department") String department,
                                  @RequestParam("rollNo") String rollNo,
                                  @RequestParam("section") String section
                                  ){
        try {
            studentService.studentRegister(name, year, department, rollNo, section);
            return "Successfully" + name + " " + rollNo;

        }catch (IllegalArgumentException e){
            return e.getMessage();
        }

    }
        @PostMapping("/mark")
        public String mark(@RequestBody AttendanceRequest request){

            attendanceService.markAttendance(request);
        return "Attendance Marked Successfully.";
        }

    @RestControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(AttendanceAlreadyMarkedException.class)
        public ResponseEntity<String> handleAttendanceAlreadyMarked(
                AttendanceAlreadyMarkedException ex) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409
                    .body(ex.getMessage());
        }
    }
}
