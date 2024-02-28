package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.CommentaireDao;
import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.models.Commentaire;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentaireController {
    @Autowired
    private CommentaireDao commentaireDao;

    @Autowired
    private RessourceDao ressourceDao;

    // récupéré les commentaires

    @GetMapping("Ressource/{id}/Commentaires")
    public ResponseEntity<List<Commentaire>> getCommentaires(@PathVariable(name = "id") Integer id){
        Ressource res = ressourceDao.getById(id);
        return new ResponseEntity(commentaireDao.findByRessource(res), HttpStatus.OK);
    }

    // ajouter un commentaire

    // supprimer un commentaire
}
