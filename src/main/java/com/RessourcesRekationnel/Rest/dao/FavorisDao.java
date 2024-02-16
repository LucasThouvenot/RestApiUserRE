package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorisDao extends JpaRepository<Favoris,Integer> {
}
