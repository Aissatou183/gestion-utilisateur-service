package com.uasz.gestion_utilisateur_service.repository;

import com.uasz.gestion_utilisateur_service.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    boolean existsByMatricule(String matricule);
}