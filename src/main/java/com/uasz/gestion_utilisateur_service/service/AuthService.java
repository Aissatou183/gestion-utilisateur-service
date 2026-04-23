package com.uasz.gestion_utilisateur_service.service;

import com.uasz.gestion_utilisateur_service.dto.AuthRequest;
import com.uasz.gestion_utilisateur_service.dto.AuthResponse;
import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import com.uasz.gestion_utilisateur_service.exception.BadRequestException;
import com.uasz.gestion_utilisateur_service.repository.UtilisateurRepository;
import com.uasz.gestion_utilisateur_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new BadRequestException("Email ou mot de passe incorrect");
        }

        String token = jwtService.generateToken(utilisateur);

        return new AuthResponse(
                utilisateur.getId(),
                token,
                "Bearer",
                utilisateur.getEmail(),
                utilisateur.getRole().name()
        );
    }
}