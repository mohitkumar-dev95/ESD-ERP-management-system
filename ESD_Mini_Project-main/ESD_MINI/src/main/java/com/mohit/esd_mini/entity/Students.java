package com.mohit.esd_mini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "students")
public class Students {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int studentId;

        private String password;

        @Column(nullable = false, unique = true)
        private Integer rollNumber;

        private String firstName;
        private String lastName;
        private String email;

        private String photographPath;
        private BigDecimal cgpa;
        private int totalCredits;

        private int graduationYear;

        @ManyToOne
        @JoinColumn(name = "domain_id", nullable = true)
        private Domain domain;

        @ManyToOne
        @JoinColumn(name = "specialisation_id", nullable = true)
        private Specialisation specialisation;

        @ManyToOne
        @JoinColumn(name = "placement_id", nullable = true)
        private Placements placement;
}
