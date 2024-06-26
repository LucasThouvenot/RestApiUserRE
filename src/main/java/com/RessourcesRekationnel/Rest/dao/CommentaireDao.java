package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Commentaire;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireDao extends JpaRepository<Commentaire, Integer> {
    public List<Commentaire> findByRessource(Ressource ressource);

    public List<Commentaire> findByUser(User user);
}
