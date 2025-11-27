package com.mohit.esd_mini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculty_courses")
public class Faculty_Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faculty_course_id;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Employees faculty;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;
}
