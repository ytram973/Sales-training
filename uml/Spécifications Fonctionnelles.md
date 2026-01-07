# Spécifications fonctionnelles — Application de vente de formations
## 1. Objectif

Application permettant :

-la consultation des formations

-la recherche et le filtrage

-la gestion panier + commande

-l’authentification simple

-Version actuelle : application console, prête à évoluer vers une application web.

## 2. Acteurs
### Visiteur (non connecté)

- consulte les formations

- recherche / filtre

- ne peut pas commander

### Utilisateur connecté

- possède login + mot de passe

- peut passer commande

### Client

- personne associée à une commande

## 3. Fonctionnalités
### 3.1 Catalogue formations

- afficher toutes les formations

- filtrer par catégorie

- filtrer par mode :

    - présentiel

    - distanciel

- rechercher par mot-clé (nom + description)

### 3.2 Panier

- ajouter / retirer une formation

- afficher panier

- calcul total

### 3.3 Utilisateur

- création de compte

- connexion / déconnexion

### 3.4 Commandes

- associer un client

- plusieurs lignes par commande

## 4. Données principales
### Formation

- id

- nom

- description

- durée (jours)

- mode (présentiel/distanciel)

- prix

- catégorie

## 5. Règles métier

- commande uniquement si connecté

- panier non vide obligatoire

- prix > 0

- quantité > 0

- login unique

## 6. Architecture

- Java console

- architecture multicouche :

- présentation(Main)

- business

- DAO

- entités(model)

- base MariaDB via JDBC

