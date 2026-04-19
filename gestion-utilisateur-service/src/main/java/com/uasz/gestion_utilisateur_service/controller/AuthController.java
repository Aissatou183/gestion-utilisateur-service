package com.uasz.gestion_utilisateur_service.controller;
import com.uasz.gestion_utilisateur_service.dto.AuthRequest;
import com.uasz.gestion_utilisateur_service.dto.AuthResponse;
import com.uasz.gestion_utilisateur_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}