package com.uasz.gestion_utilisateur_service.service;

import java.util.List;

import com.uasz.gestion_utilisateur_service.dto.*;
import com.uasz.gestion_utilisateur_service.entity.*;
import com.uasz.gestion_utilisateur_service.exception.BadRequestException;
import com.uasz.gestion_utilisateur_service.exception.ResourceNotFoundException;
import com.uasz.gestion_utilisateur_service.repository.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final EtudiantRepository etudiantRepository;
    private final EnseignantRepository enseignantRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= CREATE =================

    public UtilisateurResponse createEtudiant(CreateEtudiantRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (utilisateurRepository.existsByEmail(email)) {
            throw new BadRequestException("Cet email existe déjà");
        }

        if (etudiantRepository.existsByMatricule(request.getMatricule())) {
            throw new BadRequestException("Ce matricule existe déjà");
        }

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(request.getNom());
        etudiant.setPrenom(request.getPrenom());
        etudiant.setEmail(email);
        etudiant.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        etudiant.setRole(Role.ETUDIANT);
        etudiant.setMatricule(request.getMatricule());
        etudiant.setFiliere(request.getFiliere());
        etudiant.setNiveau(request.getNiveau());

        return mapToResponse(etudiantRepository.save(etudiant));
    }

    public UtilisateurResponse createEnseignant(CreateEnseignantRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (utilisateurRepository.existsByEmail(email)) {
            throw new BadRequestException("Cet email existe déjà");
        }

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(request.getNom());
        enseignant.setPrenom(request.getPrenom());
        enseignant.setEmail(email);
        enseignant.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        enseignant.setRole(Role.ENSEIGNANT);
        enseignant.setSpecialite(request.getSpecialite());
        enseignant.setNombreMaxProjets(
                request.getNombreMaxProjets() != null ? request.getNombreMaxProjets() : 5
        );

        return mapToResponse(enseignantRepository.save(enseignant));
    }

    // ================= READ =================

    public List<UtilisateurResponse> getAllUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UtilisateurResponse getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        return mapToResponse(utilisateur);
    }

    // ================= UPDATE =================

    public UtilisateurResponse updateEtudiant(Long id, UpdateEtudiantRequest request) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable"));

        if (!(utilisateur instanceof Etudiant etudiant)) {
            throw new BadRequestException("Cet utilisateur n'est pas un étudiant");
        }

        String email = request.getEmail().trim().toLowerCase();

        if (!etudiant.getEmail().equals(email) && utilisateurRepository.existsByEmail(email)) {
            throw new BadRequestException("Cet email existe déjà");
        }

        if (!etudiant.getMatricule().equals(request.getMatricule())
                && etudiantRepository.existsByMatricule(request.getMatricule())) {
            throw new BadRequestException("Ce matricule existe déjà");
        }

        etudiant.setNom(request.getNom());
        etudiant.setPrenom(request.getPrenom());
        etudiant.setEmail(email);
        etudiant.setMatricule(request.getMatricule());
        etudiant.setFiliere(request.getFiliere());
        etudiant.setNiveau(request.getNiveau());

        return mapToResponse(etudiantRepository.save(etudiant));
    }

    public UtilisateurResponse updateEnseignant(Long id, UpdateEnseignantRequest request) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable"));

        if (!(utilisateur instanceof Enseignant enseignant)) {
            throw new BadRequestException("Cet utilisateur n'est pas un enseignant");
        }

        String email = request.getEmail().trim().toLowerCase();

        if (!enseignant.getEmail().equals(email) && utilisateurRepository.existsByEmail(email)) {
            throw new BadRequestException("Cet email existe déjà");
        }

        enseignant.setNom(request.getNom());
        enseignant.setPrenom(request.getPrenom());
        enseignant.setEmail(email);
        enseignant.setSpecialite(request.getSpecialite());

        if (request.getNombreMaxProjets() != null) {
            enseignant.setNombreMaxProjets(request.getNombreMaxProjets());
        }

        return mapToResponse(enseignantRepository.save(enseignant));
    }

    // ================= DELETE =================

    public void deleteUtilisateur(Long id) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        if (utilisateur.getRole() == Role.ADMINISTRATEUR) {
            throw new BadRequestException("La suppression d'un administrateur est interdite");
        }

        utilisateurRepository.delete(utilisateur);
    }

    // ================= PASSWORD =================

    public void changePassword(Long id, ChangePasswordRequest request) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        if (utilisateur.getRole() == Role.ADMINISTRATEUR) {
            throw new BadRequestException("Modification interdite pour admin");
        }

        utilisateur.setMotDePasse(passwordEncoder.encode(request.getNouveauMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }

    // ================= MAPPER =================

    private UtilisateurResponse mapToResponse(Utilisateur utilisateur) {

        UtilisateurResponse.UtilisateurResponseBuilder builder = UtilisateurResponse.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole().name());

        if (utilisateur instanceof Etudiant etudiant) {
            builder.matricule(etudiant.getMatricule())
                    .filiere(etudiant.getFiliere())
                    .niveau(etudiant.getNiveau());
        }

        if (utilisateur instanceof Enseignant enseignant) {
            builder.specialite(enseignant.getSpecialite())
                    .nombreMaxProjets(enseignant.getNombreMaxProjets());
        }

        return builder.build();
    }
}