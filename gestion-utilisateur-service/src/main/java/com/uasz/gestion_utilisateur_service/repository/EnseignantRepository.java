package com.uasz.gestion_utilisateur_service.repository;
import com.uasz.gestion_utilisateur_service.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}