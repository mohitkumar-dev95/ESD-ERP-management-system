package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Courses;
import com.mohit.esd_mini.entity.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {
    @Query("SELECT c FROM Courses c WHERE c.year = :year")
    List<Courses> findCoursesByYear(@Param("year") Integer year);
}
