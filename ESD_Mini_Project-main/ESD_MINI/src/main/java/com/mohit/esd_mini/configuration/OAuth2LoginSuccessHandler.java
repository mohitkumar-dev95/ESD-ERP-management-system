package com.mohit.esd_mini.configuration;

import com.mohit.esd_mini.entity.Students;
import com.mohit.esd_mini.helper.JWTHelper;
import com.mohit.esd_mini.repo.StudentRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private JWTHelper jwtHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String givenName = oAuth2User.getAttribute("given_name");
        String familyName = oAuth2User.getAttribute("family_name");

        if (email == null) {
            response.sendRedirect("http://localhost:3000/signin?error=Email not found");
            return;
        }

        Optional<Students> existingStudent = studentRepo.findByEmail(email);
        Students student;

        if (existingStudent.isPresent()) {
            student = existingStudent.get();
        } else {
            student = new Students();
            student.setEmail(email);
            student.setFirstName(givenName != null ? givenName : name);
            student.setLastName(familyName != null ? familyName : "");
            student.setRollNumber(generateRandomRollNumber());
            student.setPassword("GOOGLE_AUTH_USER");
            studentRepo.save(student);
        }

        String jwt = jwtHelper.generateToken(student.getEmail());
        
        // Redirect to frontend with token
        response.sendRedirect("http://localhost:3000/google-callback?token=" + jwt + "&studentId=" + student.getStudentId());
    }

    private Integer generateRandomRollNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
