package com.uasz.gestion_utilisateur_service.repository;

import com.uasz.gestion_utilisateur_service.entity.Role;
import com.uasz.gestion_utilisateur_service.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}