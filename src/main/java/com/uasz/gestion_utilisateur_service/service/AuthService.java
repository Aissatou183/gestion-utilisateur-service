package com.uasz.gestion_utilisateur_service.service;
import com.uasz.gestion_utilisateur_service.dto.AuthRequest;
import com.uasz.gestion_utilisateur_service.dto.AuthResponse;
import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import com.uasz.gestion_utilisateur_service.exception.ResourceNotFoundException;
import com.uasz.gestion_utilisateur_service.repository.UtilisateurRepository;
import com.uasz.gestion_utilisateur_service.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotDePasse()
                )
        );

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

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