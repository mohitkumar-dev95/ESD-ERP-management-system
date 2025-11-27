package com.mohit.esd_mini.dto;

import java.util.List;

public record EnrollStudentRequest(
       int studentId,
       List<Integer> courses
) {
}

