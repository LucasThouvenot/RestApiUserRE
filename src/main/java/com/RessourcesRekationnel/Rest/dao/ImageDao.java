package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Image;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDao extends JpaRepository<Image, Integer> {

    public List<Image> findByRessource(Ressource ressource);
}
