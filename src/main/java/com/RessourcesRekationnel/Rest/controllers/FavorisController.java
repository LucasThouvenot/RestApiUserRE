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

    @GetMapping("/users/{id}/favoris")
    public ResponseEntity<List<Ressource>> getFavoris(@PathVariable(name = "id") Integer id){

        Optional<User> userOptional = userDao.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if (user.getFavoris() != null) {
                return new ResponseEntity<>(user.getFavoris().getRessources(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Or any other appropriate status code
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //ajoute un favoris

    @PostMapping("/users/{id}/favoris")
    public ResponseEntity<List<Ressource>> addRessource(@PathVariable(name = "id") Integer idUser,@RequestBody Ressource res) {
        Optional<User> userOptional = userDao.findById(idUser);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Optional<Ressource> ressourceOptional = ressourceDao.findById(res.getId());
            if (ressourceOptional.isPresent()) {
                Ressource ressource = ressourceOptional.get();

                Favoris favoris = user.getFavoris();
                if (favoris == null) {
                    favoris = new Favoris();
                    favoris.getRessources().add(ressource);
                    favorisDao.save(favoris);
                    user.setFavoris(favoris);
                    userDao.save(user);
                } else {
                    favoris.getRessources().add(ressource);
                    favorisDao.save(favoris);
                }

                return new ResponseEntity<>(favoris.getRessources(), HttpStatus.CREATED);


            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //supprimer un favoris

}
