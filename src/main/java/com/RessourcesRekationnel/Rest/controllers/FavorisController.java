package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.FavorisDao;
import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.Favoris;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.models.User;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FavorisController {

    private UserDao userDao;

    @GetMapping("/users/{id}/favoris")
    public ResponseEntity<List<Ressource>> getFavoris(@RequestHeader Integer id){

        if(userDao.existsById(id)){
            Favoris favoris = userDao.favoris(userDao.findById(id));
            return new ResponseEntity(favoris.getRessources(),HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
