package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Employees;
import com.mohit.esd_mini.entity.Faculty_Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmployeeId(Integer id);
}
