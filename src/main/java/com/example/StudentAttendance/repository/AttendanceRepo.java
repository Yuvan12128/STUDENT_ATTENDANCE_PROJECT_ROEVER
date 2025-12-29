package com.example.StudentAttendance.repository;

import com.example.StudentAttendance.model.Attendance;
import com.example.StudentAttendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    boolean existsByStudentAndAttDate(Student student, LocalDate attDate);
}
