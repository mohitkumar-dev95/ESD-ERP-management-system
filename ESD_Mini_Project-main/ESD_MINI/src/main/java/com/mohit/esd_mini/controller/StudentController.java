package com.mohit.esd_mini.controller;

import com.mohit.esd_mini.dto.EnrollStudentRequest;
import com.mohit.esd_mini.dto.StudentRequest;
import jakarta.validation.Valid;
import com.mohit.esd_mini.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class StudentController {

    // final : it will create only one object in whole project container, when we run the project
    @Autowired
    private StudentService studentService;

    @GetMapping("/show_courses")
    public ResponseEntity<List<Map<String, Object>>> showCourses(
            @RequestParam String studentId) {
        return ResponseEntity.ok(studentService.showAllowedCourses(studentId));
    }

    @GetMapping("/enrolled_courses")
    public ResponseEntity<List<Map<String, Object>>> enrolledCourses(
            @RequestParam String studentId) {
        return ResponseEntity.ok(studentService.showEnrolledCourses(studentId));
    }

    @PostMapping("/create_account")
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        System.out.println("==================== create controller");
        return ResponseEntity.ok(studentService.createStudent(studentRequest));
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestBody @Valid EnrollStudentRequest enrollStudentRequest) {
        return ResponseEntity.ok(studentService.enrollStudent(enrollStudentRequest));
    }

    @PostMapping("/deenroll")
    public ResponseEntity<String> deenrollStudent(@RequestBody @Valid EnrollStudentRequest enrollStudentRequest) throws BadRequestException {
        return ResponseEntity.ok(studentService.deenrollStudent(enrollStudentRequest));
    }

}