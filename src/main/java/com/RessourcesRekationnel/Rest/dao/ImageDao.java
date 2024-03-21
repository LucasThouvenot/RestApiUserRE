package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Images;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDao extends JpaRepository<Images, Integer> {

    public List<Images> findByRessource(Ressource ressource);
}
