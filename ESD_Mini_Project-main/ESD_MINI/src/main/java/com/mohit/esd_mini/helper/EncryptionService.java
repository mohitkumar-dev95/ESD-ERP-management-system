package com.mohit.esd_mini.helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validates(String password, String encodedPassword) {
        System.out.println("PasswordEncoder instance: " + passwordEncoder.getClass().getName());
        return passwordEncoder.matches(password, encodedPassword);
    }
}
