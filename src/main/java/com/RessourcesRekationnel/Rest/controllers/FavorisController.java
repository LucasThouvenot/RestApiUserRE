package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.FavorisDao;
import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.models.User;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FavorisController {
    @Autowired
    private UserDao userDao;

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

    //supprimer un favoris

}
