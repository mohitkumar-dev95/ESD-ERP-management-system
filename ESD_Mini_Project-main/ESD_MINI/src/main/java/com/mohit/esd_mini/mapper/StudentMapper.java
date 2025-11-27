package com.mohit.esd_mini.mapper;

import com.mohit.esd_mini.dto.StudentRequest;
import com.mohit.esd_mini.entity.Domain;
import com.mohit.esd_mini.entity.Placements;
import com.mohit.esd_mini.entity.Specialisation;
import com.mohit.esd_mini.entity.Students;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Students toEntity(StudentRequest request) {
        // Assuming StudentRequest contains all necessary fields, including IDs or details for related entities
        Domain domain = Domain.builder()
                .domain_id(request.domainId()) // Replace `domainId()` with actual method to fetch domain ID if needed
                .build();

        Specialisation specialisation = Specialisation.builder()
                .specialisation_id(request.specialisationId()) // Replace `specialisationId()` with actual method
                .build();

        Placements placement = Placements.builder()
                .placement_id(request.placementId()) // Replace `placementId()` with actual method
                .build();

        return Students.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .rollNumber(request.rollNumber())
                .photographPath(request.photographPath())
                .cgpa(request.cgpa())
                .totalCredits(request.totalCredits())
                .graduationYear(request.graduationYear())
                .domain(domain)
                .specialisation(specialisation)
                .placement(placement)
                .build();
    }
}
