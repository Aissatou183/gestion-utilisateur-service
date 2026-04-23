package com.uasz.gestion_utilisateur_service.config;

import com.uasz.gestion_utilisateur_service.entity.Role;
import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import com.uasz.gestion_utilisateur_service.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            String adminEmail = "admin@uasz.sn";

            if (!utilisateurRepository.existsByEmail(adminEmail)) {
                Utilisateur admin = new Utilisateur();
                admin.setNom("Admin");
                admin.setPrenom("UASZ");
                admin.setEmail(adminEmail);
                admin.setMotDePasse(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMINISTRATEUR);

                utilisateurRepository.save(admin);
            }
        };
    }
}