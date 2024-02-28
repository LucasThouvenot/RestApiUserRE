package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorisDao extends JpaRepository<Favoris,Integer> {

}
