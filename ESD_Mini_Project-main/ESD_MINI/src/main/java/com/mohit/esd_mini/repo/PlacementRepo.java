package com.mohit.esd_mini.repo;

import com.mohit.esd_mini.entity.Placements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacementRepo extends JpaRepository<Placements, Long> {
//    Optional<Students> findByEmail(String email);
}
