package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.FavorisDao;
import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.models.User;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FavorisController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private FavorisDao favorisDao;

    @Autowired
    private RessourceDao ressourceDao;

    // Récupérer les favoris d'un utilisateur
    @GetMapping("/users/{id}/favoris")
    public ResponseEntity<List<Ressource>> getFavoris(@PathVariable(name = "id") Integer id){
        try {
            Optional<User> userOptional = userDao.findById(id);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                if (user.getFavoris() != null) {
                    return new ResponseEntity<>(user.getFavoris().getRessources(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Pas de favoris trouvés
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Erreur interne du serveur
        }
    }

    // Ajouter une ressource aux favoris d'un utilisateur
    @PostMapping("/users/{id}/favoris")
    public ResponseEntity<List<Ressource>> addRessource(@PathVariable(name = "id") Integer idUser,@RequestParam(name="idRes") Integer idRes) {
        try {
            Optional<User> userOptional = userDao.findById(idUser);
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                Optional<Ressource> ressourceOptional = ressourceDao.findById(idRes);
                if (ressourceOptional.isPresent()) {
                    Ressource ressource = ressourceOptional.get();

                    Favoris favoris = user.getFavoris();
                    if (favoris == null) {
                        favoris = new Favoris();
                        favoris.setRessources(new ArrayList<>());
                        favoris.addRessources(ressource);
                        favorisDao.save(favoris);
                        user.setFavoris(favoris);
                        userDao.save(user);
                    } else {
                        favoris.getRessources().add(ressource);
                        favorisDao.save(favoris);
                    }

                    return new ResponseEntity<>(favoris.getRessources(), HttpStatus.OK); // Renvoyer les favoris mis à jour
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Ressource non trouvée
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Erreur interne du serveur
        }
    }

    // Supprimer une ressource des favoris d'un utilisateur
    @DeleteMapping("users/{id}/favoris/{idRes}")
    public ResponseEntity<List<Ressource>> removeRessource(@PathVariable(name = "id") Integer idUser,@PathVariable(name="idRes")Integer idRes) {
        try {
            Optional<User> userOptional = userDao.findById(idUser);
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                Optional<Ressource> ressourceOptional = ressourceDao.findById(idRes);
                if (ressourceOptional.isPresent()) {
                    Ressource ressource = ressourceOptional.get();

                    Favoris favoris = user.getFavoris();
                    if (favoris == null) {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Pas de favoris pour cet utilisateur
                    } else {
                        favoris.delRessource(ressource);
                        favorisDao.save(favoris);
                    }

                    return new ResponseEntity<>(favoris.getRessources(), HttpStatus.OK); // Renvoyer les favoris mis à jour
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Ressource non trouvée
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Erreur interne du serveur
        }
    }

}
