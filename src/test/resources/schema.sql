-- Table: favoris
CREATE TABLE IF NOT EXISTS favoris (
    id INT NOT NULL AUTO_INCREMENT,
    label VARCHAR(255),
    PRIMARY KEY (id)
);

-- Table: utilisateur
CREATE TABLE IF NOT EXISTS utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    est_actif BOOLEAN DEFAULT TRUE,
    est_admin BOOLEAN DEFAULT FALSE,
    adresse_mail VARCHAR(255),
    favoris_id INT,
    image_url VARCHAR(255),
    nom VARCHAR(255),
    numero_telephone VARCHAR(20),
    mot_de_passe VARCHAR(255),
    prenom VARCHAR(255),
    nom_utilisateur VARCHAR(255),
    token VARCHAR(255),
    CONSTRAINT FK_utilisateur_favoris FOREIGN KEY (favoris_id) REFERENCES favoris(id)
);

-- Table: ressource
CREATE TABLE IF NOT EXISTS ressource (
    id INT NOT NULL AUTO_INCREMENT,
    content VARCHAR(255),
    date_publication DATE,
    exploite BOOLEAN DEFAULT FALSE,
    est_public BOOLEAN DEFAULT TRUE,
    titre VARCHAR(255),
    user_id INT,
    PRIMARY KEY (id),
    CONSTRAINT FK_ressource_utilisateur FOREIGN KEY (user_id) REFERENCES utilisateur(id)
);

-- Table: image
CREATE TABLE IF NOT EXISTS image (
    id INT NOT NULL AUTO_INCREMENT,
    ressource_id INT,
    image LONGBLOB,
    PRIMARY KEY (id),
    CONSTRAINT FK_image_ressource FOREIGN KEY (ressource_id) REFERENCES ressource(id)
);

-- Table: commentaire
CREATE TABLE IF NOT EXISTS commentaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_publication DATE,
    ressource_id INT,
    utilisateur_id INT,
    contenu VARCHAR(255),
    CONSTRAINT fk_base_user_re_ressource FOREIGN KEY (ressource_id) REFERENCES ressource(id),
    CONSTRAINT fk_base_user_re_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
);

-- Table: favoris_ressources
CREATE TABLE IF NOT EXISTS favoris_ressources (
    fav_id INT NOT NULL,
    res_id INT NOT NULL,
    CONSTRAINT FK_favoris_ressources_favoris FOREIGN KEY (fav_id) REFERENCES favoris(id),
    CONSTRAINT FK_favoris_ressources_ressource FOREIGN KEY (res_id) REFERENCES ressource(id)
);

-- Table: ressource_image
CREATE TABLE IF NOT EXISTS ressource_image (
    image_id INT NOT NULL,
    ressource_id INT NOT NULL,
    CONSTRAINT FK_ressources_images_image FOREIGN KEY (image_id) REFERENCES image(id),
    CONSTRAINT FK_ressources_images_ressource FOREIGN KEY (ressource_id) REFERENCES ressource(id)
);
