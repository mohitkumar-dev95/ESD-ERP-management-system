package com.mohit.esd_mini.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "YEAR")
    private int year;

    @Column(nullable = false)
    private int term;

    private int faculty_id;
    private int credits;
    private int capacity;
}
