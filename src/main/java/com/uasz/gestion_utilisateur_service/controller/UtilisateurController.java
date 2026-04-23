package com.uasz.gestion_utilisateur_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.uasz.gestion_utilisateur_service.dto.ChangePasswordRequest;
import com.uasz.gestion_utilisateur_service.dto.CreateEnseignantRequest;
import com.uasz.gestion_utilisateur_service.dto.CreateEtudiantRequest;
import com.uasz.gestion_utilisateur_service.dto.UpdateEnseignantRequest;
import com.uasz.gestion_utilisateur_service.dto.UpdateEtudiantRequest;
import com.uasz.gestion_utilisateur_service.dto.UtilisateurResponse;
import com.uasz.gestion_utilisateur_service.service.UtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping("/etudiants")
    public ResponseEntity<UtilisateurResponse> createEtudiant(
            @Validated @RequestBody CreateEtudiantRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.createEtudiant(request));
    }

    @PostMapping("/enseignants")
    public ResponseEntity<UtilisateurResponse> createEnseignant(
            @Validated @RequestBody CreateEnseignantRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.createEnseignant(request));
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponse>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @GetMapping("/enseignants")
    public ResponseEntity<List<UtilisateurResponse>> getAllEnseignants() {
        return ResponseEntity.ok(utilisateurService.getAllEnseignants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> getUtilisateurById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    @PutMapping("/etudiants/{id}")
    public ResponseEntity<UtilisateurResponse> updateEtudiant(
            @PathVariable Long id,
            @Validated @RequestBody UpdateEtudiantRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.updateEtudiant(id, request));
    }

    @PutMapping("/enseignants/{id}")
    public ResponseEntity<UtilisateurResponse> updateEnseignant(
            @PathVariable Long id,
            @Validated @RequestBody UpdateEnseignantRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.updateEnseignant(id, request));
    }

    @PatchMapping("/{id}/mot-de-passe")
    public ResponseEntity<Map<String, Object>> changePassword(
            @PathVariable Long id,
            @Validated @RequestBody ChangePasswordRequest request
    ) {
        utilisateurService.changePassword(id, request);
        return ResponseEntity.ok(Map.of("message", "Mot de passe réinitialisé avec succès"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok(Map.of("message", "Utilisateur supprimé avec succès"));
    }
    @GetMapping("/etudiants")
    public ResponseEntity<List<UtilisateurResponse>> getAllEtudiants() {
        return ResponseEntity.ok(utilisateurService.getAllEtudiants());
    }
}