-- 1. Création base de données
DROP DATABASE IF EXISTS training_shop;
CREATE DATABASE training_shop
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE training_shop;

-- 2. Table formation
DROP TABLE IF EXISTS formation;

CREATE TABLE formation (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description TEXT NOT NULL,
  duration_days INT UNSIGNED NOT NULL,
  mode ENUM('PRESENTIEL', 'DISTANCIEL') NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  category VARCHAR(100) NOT NULL
);

-- 3. Données de test (catalogue de formations)
INSERT INTO formation (name, description, duration_days, mode, price, category) VALUES
('Python débutant',
 'Découvrir les bases du langage Python : variables, conditions, boucles, fonctions.',
 3,
 'DISTANCIEL',
 450.00,
 'Programmation'),

('Java fondamentaux',
 'Notions de base en Java : POO, collections, exceptions, bonnes pratiques.',
 5,
 'PRESENTIEL',
 850.00,
 'Programmation'),

('Java avancé',
 'Approfondissement Java : streams, lambdas, multithreading, design patterns.',
 4,
 'DISTANCIEL',
 990.00,
 'Programmation'),

('Introduction aux bases de données SQL',
 'Modélisation, requêtes SELECT, JOIN, contraintes, normalisation.',
 2,
 'PRESENTIEL',
 400.00,
 'Base de données'),

('Développement Web avec HTML/CSS/JS',
 'Créer des interfaces web responsives avec HTML5, CSS3 et JavaScript.',
 4,
 'DISTANCIEL',
 720.00,
 'Web'),

('Spring Boot pour les APIs REST',
 'Créer des APIs REST avec Spring Boot, JPA, validation et sécurité basique.',
 3,
 'DISTANCIEL',
 990.00,
 'Java'),

('Git et bonnes pratiques',
 'Utiliser Git en équipe : branches, merge, rebase, pull request, workflow.',
 1,
 'PRESENTIEL',
 250.00,
 'Outils'),

('Architecture et design orienté objet',
 'Principes SOLID, patterns de conception, organisation d’un projet.',
 2,
 'DISTANCIEL',
 600.00,
 'Architecture'),

('Python pour la data',
 'Manipulation de données, bases de NumPy et Pandas, premiers graphiques.',
 3,
 'DISTANCIEL',
 780.00,
 'Data'),

('Communication et travail en équipe',
 'Soft skills pour mieux collaborer en projet IT.',
 1,
 'PRESENTIEL',
 300.00,
 'Soft skills');


