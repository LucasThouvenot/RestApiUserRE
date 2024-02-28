package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.CommentaireDao;
import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.models.Commentaire;
import com.RessourcesRekationnel.Rest.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentaireController {
    @Autowired
    private CommentaireDao commentaireDao;

    @Autowired
    private RessourceDao ressourceDao;

    @GetMapping("Ressource/{id}/Commentaires")
    public List<Commentaire> getCommentaires(@RequestHeader Integer id){
        Ressource res = ressourceDao.getById(id);
        return commentaireDao.findByRessource(res);
    }
}
