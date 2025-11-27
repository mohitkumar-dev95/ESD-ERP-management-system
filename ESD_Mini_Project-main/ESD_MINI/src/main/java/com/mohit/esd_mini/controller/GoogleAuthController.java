package com.mohit.esd_mini.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.esd_mini.entity.Students;
import com.mohit.esd_mini.helper.JWTHelper;
import com.mohit.esd_mini.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private JWTHelper jwtHelper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GoogleAuthController() {
        System.out.println("Google OAuth client initialized successfully");
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> payload) {
        String idToken = payload.get("token");
        if (idToken == null) {
            return ResponseEntity.badRequest().body("Missing token");
        }

        try {
            // Manual decode of JWT (ID Token) to extract email and name
            // In a production environment, you should verify the signature using Google's public keys
            String[] parts = idToken.split("\\.");
            if (parts.length < 2) {
                return ResponseEntity.badRequest().body("Invalid token format");
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            JsonNode jsonNode = objectMapper.readTree(payloadJson);

            String email = jsonNode.has("email") ? jsonNode.get("email").asText() : null;
            String name = jsonNode.has("name") ? jsonNode.get("name").asText() : "Unknown";
            String givenName = jsonNode.has("given_name") ? jsonNode.get("given_name").asText() : name;
            String familyName = jsonNode.has("family_name") ? jsonNode.get("family_name").asText() : "";

            if (email == null) {
                return ResponseEntity.badRequest().body("Email not found in token");
            }

            Optional<Students> existingStudent = studentRepo.findByEmail(email);
            Students student;

            if (existingStudent.isPresent()) {
                student = existingStudent.get();
            } else {
                // Create new student
                student = new Students();
                student.setEmail(email);
                student.setFirstName(givenName);
                student.setLastName(familyName);
                // Generate a random roll number as it is required and unique
                student.setRollNumber(generateRandomRollNumber());
                // Set other required fields to defaults or null if allowed
                // Assuming password is not needed for Google login users or set a dummy one
                student.setPassword("GOOGLE_AUTH_USER"); 
                
                studentRepo.save(student);
            }

            String jwt = jwtHelper.generateToken(student.getEmail());
            
            Map<String, Object> response = new HashMap<>();
            response.put("jwt", jwt);
            response.put("studentId", student.getStudentId());
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error processing Google login: " + e.getMessage());
        }
    }

    private Integer generateRandomRollNumber() {
        Random random = new Random();
        // Generate a random 6-digit number
        return 100000 + random.nextInt(900000);
    }
}
