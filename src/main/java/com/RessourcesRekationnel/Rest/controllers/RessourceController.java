package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RessourceController {

    @Autowired
    private RessourceDao ressourceDao;

//    public ResponseEntity<Ressource> getRecentRessources(Integer id){
//
//    }

    @GetMapping("/ressources")
    public ResponseEntity<List<Ressource>> getAllRessouces(){
        return new ResponseEntity<>(ressourceDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ressources/{id}")
    public ResponseEntity<Ressource> getOneRessources(@PathVariable(name = "id") Integer id){
        if(ressourceDao.existsById(id)){
            return new ResponseEntity<>(ressourceDao.findById(id).orElse(new Ressource()),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
