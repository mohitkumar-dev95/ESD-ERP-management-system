package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.CoursePrerequisite;
import com.mohit.esd_mini.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite, Integer> {
    @Query("""
        SELECT cp.prerequisite.course_id
        FROM CoursePrerequisite cp
        WHERE cp.course.course_id = :courseId AND cp.prerequisite.course_id NOT IN (
            SELECT sc.course.course_id
            FROM Student_Courses sc
            WHERE sc.student.studentId = :studentId
        )
    """)
    List<Integer> findUnmetPrerequisites(int studentId, int courseId);

    // Query to find prerequisites for a course
    @Query("SELECT p FROM Courses p JOIN CoursePrerequisite cp " +
            "ON p.course_id = cp.prerequisite.course_id WHERE cp.course.course_id = :courseId")
    List<Courses> findPrerequisitesByCourseId(int courseId);
}
