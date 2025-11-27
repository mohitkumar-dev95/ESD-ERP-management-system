package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialisationRepo extends JpaRepository<Specialisation, Long> {
//    Optional<Students> findByEmail(String email);
}
