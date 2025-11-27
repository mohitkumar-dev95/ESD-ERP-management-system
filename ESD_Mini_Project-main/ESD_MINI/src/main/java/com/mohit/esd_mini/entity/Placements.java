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
@Table(name = "Placements")
public class Placements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placement_id;
}
