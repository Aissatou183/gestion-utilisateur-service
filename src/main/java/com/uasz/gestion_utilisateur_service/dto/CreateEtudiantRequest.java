package com.uasz.gestion_utilisateur_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEtudiantRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Email(message = "Format d'email invalide")
    @NotBlank(message = "L'email est obligatoire")
    @Pattern(
            regexp = "^[a-z]\\.[a-z0-9]+@zig\\.univ\\.sn$",
            message = "Email étudiant invalide (ex: a.d332@zig.univ.sn)"
    )
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;

    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    private String filiere;
    private String niveau;
}