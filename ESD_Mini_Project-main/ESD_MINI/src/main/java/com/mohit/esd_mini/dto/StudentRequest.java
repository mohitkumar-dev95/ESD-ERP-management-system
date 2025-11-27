package com.mohit.esd_mini.dto;

import java.math.BigDecimal;
import java.time.Year;

public record StudentRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        int rollNumber,
        String photographPath,
        BigDecimal cgpa,
        int totalCredits,
        int graduationYear,
        int domainId,
        int specialisationId,
        int placementId
) {
}
