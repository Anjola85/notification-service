package com.quiikmart.notificationservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/all")
    public List<AuthEntity> getAllAuthEntities() {
        return authRepository.findAll();
    }
}