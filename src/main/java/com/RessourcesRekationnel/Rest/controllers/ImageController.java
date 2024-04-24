package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.ImageDao;
import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private RessourceDao ressourceDao;

    @Autowired
    private ImageDao imageDao;

    @GetMapping("/ressources/{id}/images")
    public ResponseEntity<List<Image>> getRessourceImages(@PathVariable(name = "id")Integer id){
        if(ressourceDao.existsById(id)){
            return new ResponseEntity<>(imageDao.findByRessource(ressourceDao.findById(id).orElse(null)),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
