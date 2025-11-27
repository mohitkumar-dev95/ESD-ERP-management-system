package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DomainRepo extends JpaRepository<Domain, Long> {
//    Optional<Students> findByEmail(String email);
}
