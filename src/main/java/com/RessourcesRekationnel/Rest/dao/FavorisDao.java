package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorisDao extends JpaRepository<Favoris,Integer> {

}
