# 🎓 Système de Gestion des Utilisateurs - UASZ

## 📌 Description

Ce projet est un microservice développé dans le cadre du système de gestion des projets académiques de l’Université Assane Seck de Ziguinchor (UASZ).

Il permet de gérer les utilisateurs de la plateforme, notamment les administrateurs, les enseignants et les étudiants, avec une authentification sécurisée basée sur JWT.

---

## 🎯 Objectifs

Ce microservice a pour objectifs de :

- gérer les comptes utilisateurs ;
- assurer l’authentification sécurisée ;
- implémenter la gestion des rôles ;
- permettre l’administration des utilisateurs ;
- sécuriser les accès via JWT.

---

## 🧱 Architecture

Le système repose sur une architecture simple autour de trois composants :

- **Frontend** : application React ;
- **Backend** : microservice Spring Boot ;
- **Base de données** : MySQL.

Architecture simplifiée :

```text
Frontend (React)
       ↓
Backend (Spring Boot)
       ↓
Base de données (MySQL)