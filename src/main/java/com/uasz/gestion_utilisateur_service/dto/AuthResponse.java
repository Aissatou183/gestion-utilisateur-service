package com.uasz.gestion_utilisateur_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String token;
    private String type;
    private String email;
    private String role;
}