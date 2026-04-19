package com.uasz.gestion_utilisateur_service.repository;
import java.util.Optional;

import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
}