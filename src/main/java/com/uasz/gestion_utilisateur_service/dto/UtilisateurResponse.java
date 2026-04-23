package com.uasz.gestion_utilisateur_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UtilisateurResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String matricule;
    private String filiere;
    private String niveau;
    private String specialite;
    private Integer nombreMaxProjets;
}