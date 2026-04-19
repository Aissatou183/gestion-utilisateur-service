# 🎓 Système de Gestion des Utilisateurs - UASZ

## 📌 1. Contexte du projet

Dans le cadre de la digitalisation des processus académiques à l'Université Assane Seck de Ziguinchor (UASZ), ce projet vise à développer un système d'information permettant de gérer efficacement les projets académiques (mémoires, projets tutorés, etc.).

Ce dépôt correspond au **microservice de gestion des utilisateurs**, qui constitue la base du système.

---

## 🎯 2. Objectifs

Ce microservice a pour objectifs :

- Gérer les comptes utilisateurs
- Assurer l’authentification sécurisée
- Implémenter une gestion des rôles
- Permettre l'administration des utilisateurs
- Garantir la sécurité des accès via JWT

---

## 🧱 3. Architecture du système

Le système repose sur une architecture **microservices** :

---

## ⚙️ 4. Technologies utilisées

### 🔧 Backend
- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)

### 🗄️ Base de données
- MySQL

### 🛠️ Outils
- Maven
- Postman
- Git / GitHub

---

## 🔐 5. Sécurité

Le système implémente :

- Authentification basée sur JWT
- Autorisation par rôles
- Mots de passe hashés (sécurisés)

### 👥 Rôles disponibles :

- `ADMINISTRATEUR`
- `ENSEIGNANT`
- `ETUDIANT`

---

## 📡 6. API REST - Endpoints

### 🔑 Authentification

| Méthode | Endpoint            | Description |
|--------|--------------------|------------|
| POST   | /api/auth/login    | Connexion utilisateur |

---

### 👥 Utilisateurs

| Méthode | Endpoint                                | Description |
|--------|----------------------------------------|------------|
| GET    | /api/utilisateurs                      | Liste des utilisateurs |
| POST   | /api/utilisateurs/etudiants            | Ajouter un étudiant |
| POST   | /api/utilisateurs/enseignants          | Ajouter un enseignant |
| PUT    | /api/utilisateurs/{id}                 | Modifier un utilisateur |
| DELETE | /api/utilisateurs/{id}                 | Supprimer un utilisateur |

---

### 🔒 Mot de passe

| Méthode | Endpoint                                  |
|--------|------------------------------------------|
| PATCH  | /api/utilisateurs/{id}/mot-de-passe       |

---

## 🚀 7. Installation et exécution

### 1️⃣ Cloner le projet

```bash
git clone https://github.com/Aissatou183/gestion-utilisateur-service.git
cd gestion-utilisateur-service
