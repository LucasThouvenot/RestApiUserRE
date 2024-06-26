package com.RessourcesRekationnel.Rest.dao;

import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByPseudo(String pseudo);

    Favoris favoris(Optional<User> user);

}
