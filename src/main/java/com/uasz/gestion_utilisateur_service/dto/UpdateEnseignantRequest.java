package com.uasz.gestion_utilisateur_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEnseignantRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Email(message = "Format d'email invalide")
    @NotBlank(message = "L'email est obligatoire")
    @Pattern(
            regexp = "^[a-z]\\.[a-z]+@univ-zig\\.sn$",
            message = "Email enseignant invalide (ex: m.gaye@univ-zig.sn)"
    )
    private String email;

    private String specialite;

    @Min(value = 1, message = "Le nombre maximal de projets doit être supérieur à 0")
    private Integer nombreMaxProjets;
}