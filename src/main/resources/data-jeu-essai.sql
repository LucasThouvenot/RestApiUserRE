-- Insertion d'un favori lié à un utilisateur
INSERT INTO favoris(label) VALUES ('un favori');

-- Insertion d'un utilisateur avec un favori
INSERT INTO utilisateur(nom_utilisateur, mot_de_passe, est_admin, nom, prenom, adresse_mail, numero_telephone, est_actif, favoris_id)
VALUES ('admin', '$2y$10$G8wbzQocBNtLJ/n3yrq/Cu6DyWhhuZRaAI.mKbxoSqWM6ejbnOJ/i', true, 'admin', 'admin', 'admin@RessourcesRelationnel.com', '0102030405', true, 1),
       ('Lucas', '$2y$10$GJTJvxN.RQliiptIDMbRpuB94PSB6Sav.odK7cbob7b965VViIo.y', false, 'Thouvenot', 'Lucas', 'thouvenot-lucas@outlook.fr', '0102030405', true, NULL);

-- Insertion d'une ressource
INSERT INTO ressource (titre,date_publication, est_public, exploite, content,user_id) VALUES
('Titre de la première publication','2024-01-12', true, true, 'text',2),
('Titre de la deuxième publication','2024-01-12', true, true, 'text',2),
('Titre de la troisième publication','2024-01-12', true, true, 'text',2),
('Titre de la quatrième publication','2024-01-12', true, true, 'text',2),
('Titre de la cinquième publication','2024-01-12', true, true, 'text',2),
('Titre de la sixième publication','2024-01-12', true, true, 'text',2),
('Titre de la septième publication','2024-01-12', true, true, 'text',2),
('Titre de la huitième publication','2024-01-12', true, true, 'text',2),
('Titre de la neuvième publication','2024-01-12', true, true, 'text',2),
('Titre de la dixième publication','2024-01-12', true, false, 'text',2),
('Titre de la onzième publication','2024-01-12', false, true, 'text',2),
('Titre de la douzième publication','2024-01-12', false, true, 'text',2),
('Titre de la treizième publication','2024-01-12', false, true, 'text',2),
('Titre de la quatorzième publication','2024-01-12', false, true, 'text',2),
('Titre de la quinzième publication','2024-01-12', false, true, 'text',2),
('Titre de la seizième publication','2024-01-12', false, false, 'text',2),
('Titre de la dix-septième publication','2024-01-12', false, false, 'text',2),
('Titre de la dix-huitième publication','2024-01-12', false, false, 'text',2),
('Titre de la dix-neuvième publication','2024-01-12', false, false, 'text',2),
('Titre de la vingtième publication','2024-01-12', false, false, 'text',2);


-- Insertion de commentaires liés à une ressource
INSERT INTO commentaire(date_publication, contenu, ressource_id, utilisateur_id) VALUES ('2024-01-12', 'abcdfgrfjdzjoezfjn', 1, 1), ('2024-02-12', 'poiuytterere', 1, 2);

-- Insertion d'une association entre un favori et une ressource
INSERT INTO favoris_ressources(fav_id, res_id) VALUES (1, 1);

--INSERT INTO image(image,ressource_id) VALUES ()
