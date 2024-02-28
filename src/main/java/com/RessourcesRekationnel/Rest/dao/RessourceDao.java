package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Commentaire;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceDao extends JpaRepository<Ressource, Integer> {
}
