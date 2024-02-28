-- Insertion d'un favori lié à un utilisateur
INSERT INTO favoris(label) VALUES ("un favori");

-- Insertion d'un utilisateur avec un favori
INSERT INTO utilisateur(nom_utilisateur, mot_de_passe, est_admin, nom, prenom, adresse_mail, numero_telephone, est_actif, favoris_id)
VALUES ('admin', '$2y$10$G8wbzQocBNtLJ/n3yrq/Cu6DyWhhuZRaAI.mKbxoSqWM6ejbnOJ/i', true, 'admin', 'admin', 'admin@RessourcesRelationnel.com', '0102030405', true, 1),
       ('Lucas', '$2y$10$GJTJvxN.RQliiptIDMbRpuB94PSB6Sav.odK7cbob7b965VViIo.y', false, 'Thouvenot', 'Lucas', 'thouvenot-lucas@outlook.fr', '0102030405', true, null);

-- Insertion d'une ressource
INSERT INTO ressource (label) VALUES
("une ressource"),
("Titre de la première publication Facebook"),
("Titre de la deuxième publication Facebook"),
("Titre de la troisième publication Facebook"),
("Titre de la quatrième publication Facebook"),
("Titre de la cinquième publication Facebook"),
("Titre de la sixième publication Facebook"),
("Titre de la septième publication Facebook"),
("Titre de la huitième publication Facebook"),
("Titre de la neuvième publication Facebook"),
("Titre de la dixième publication Facebook"),
("Titre de la onzième publication Facebook"),
("Titre de la douzième publication Facebook"),
("Titre de la treizième publication Facebook"),
("Titre de la quatorzième publication Facebook"),
("Titre de la quinzième publication Facebook"),
("Titre de la seizième publication Facebook"),
("Titre de la dix-septième publication Facebook"),
("Titre de la dix-huitième publication Facebook"),
("Titre de la dix-neuvième publication Facebook"),
("Titre de la vingtième publication Facebook");


-- Insertion de commentaires liés à une ressource
INSERT INTO commentaire(date_publication, contenu, ressource_id,utilisateur_id) VALUES ('2024-01-12', 'abcdfgrfjdzjoezfjn', 1,2), ('2024-02-12', 'poiuytterere', 1,2);

-- Insertion d'une association entre un favori et une ressource
INSERT INTO favoris_ressources(fav_id, res_id) VALUES (1, 1);
