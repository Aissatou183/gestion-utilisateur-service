package com.uasz.gestion_utilisateur_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etudiants")
public class Etudiant extends Utilisateur {

    @Column(nullable = false, unique = true, length = 50)
    private String matricule;

    @Column(length = 100)
    private String filiere;

    @Column(length = 50)
    private String niveau;
}