package com.example.StudentAttendance.controller;

import com.example.StudentAttendance.model.Student;

import com.example.StudentAttendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

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

}
