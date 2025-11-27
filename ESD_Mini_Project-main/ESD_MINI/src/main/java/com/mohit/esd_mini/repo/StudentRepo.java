package com.mohit.esd_mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.mohit.esd_mini.entity.Students;
public interface StudentRepo extends JpaRepository<Students, Integer> {
    Optional<Students> findByEmail(String email);
}
