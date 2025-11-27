package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Courses;
import com.mohit.esd_mini.entity.Faculty_Courses;
import com.mohit.esd_mini.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FacultyCoursesRepo extends JpaRepository<Faculty_Courses, Integer> {
    @Query("SELECT fc.faculty.employeeId FROM Faculty_Courses fc WHERE fc.course = :course")
    Integer findFacultyIdByCourse(@Param("course") Courses course);

}
