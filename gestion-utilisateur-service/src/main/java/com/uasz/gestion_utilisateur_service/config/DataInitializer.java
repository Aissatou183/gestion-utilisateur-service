package com.uasz.gestion_utilisateur_service.config;
import com.uasz.gestion_utilisateur_service.entity.Role;
import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import com.uasz.gestion_utilisateur_service.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@uasz.sn";

        utilisateurRepository.findByEmail(adminEmail).ifPresentOrElse(
                utilisateur -> {},
                () -> {
                    Utilisateur admin = new Utilisateur();
                    admin.setNom("Admin");
                    admin.setPrenom("UASZ");
                    admin.setEmail(adminEmail);
                    admin.setMotDePasse(passwordEncoder.encode("admin123"));
                    admin.setRole(Role.ADMINISTRATEUR);
                    utilisateurRepository.save(admin);
                }
        );
    }
}