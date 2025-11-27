package com.mohit.esd_mini.controller;

import com.mohit.esd_mini.dto.LoginRequest;
import com.mohit.esd_mini.dto.LoginResponse;
import com.mohit.esd_mini.service.LoginService;
import com.mohit.esd_mini.helper.EncryptionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // ⭐ You forgot this — now Spring knows the bean
    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody @Valid LoginRequest request) {
        System.out.println("==================== login");
        return ResponseEntity.ok(loginService.loginUser(request));
    }

    // -------- TEMP TOOL TO GENERATE HASH --------
    @PostMapping("/generate-hash")
    public String generateHash(@RequestParam String password) {
        return encryptionService.encode(password);
    }


}