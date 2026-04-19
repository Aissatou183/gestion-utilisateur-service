package com.uasz.gestion_utilisateur_service.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enseignants")
public class Enseignant extends Utilisateur {

    @Column(length = 100)
    private String specialite;

    @Column(nullable = false)
    private Integer nombreMaxProjets = 5;
}