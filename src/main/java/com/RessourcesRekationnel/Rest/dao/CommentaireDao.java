package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireDao extends JpaRepository<Commentaire, Integer> {
}
