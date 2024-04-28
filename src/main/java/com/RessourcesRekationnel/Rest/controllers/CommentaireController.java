package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.CommentaireDao;
import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.Commentaire;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentaireController {

    @Autowired
    private CommentaireDao commentaireDao;

    @Autowired
    private RessourceDao ressourceDao;

    @Autowired
    private UserDao userDao;

    // Récupérer les commentaires d'une ressource
    @GetMapping("/ressources/{id}/commentaires")
    public ResponseEntity<List<Commentaire>> getCommentaires(@PathVariable(name = "id") Integer id) {
        try {
            Ressource ressource = ressourceDao.findById(id).orElse(null);
            if (ressource != null) {
                List<Commentaire> commentaires = commentaireDao.findByRessource(ressource);
                return ResponseEntity.ok(commentaires);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer les commentaires d'un utilisateur
    @GetMapping("/users/{id}/commentaires")
    public ResponseEntity<List<Commentaire>> getUsersCommenaires(@PathVariable(name = "id") Integer id) {
        try {
            User user = userDao.findById(id).orElse(null);
            if (user != null) {
                List<Commentaire> commentaires = commentaireDao.findByUser(user);
                return ResponseEntity.ok(commentaires);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Ajouter un commentaire à une ressource
    @PostMapping("/ressources/{id}/commentaires")
    public ResponseEntity<Commentaire> addCommentaire(
            @PathVariable(name = "id") Integer id,
            @RequestBody Commentaire commentaire) {
        try {
            Ressource ressource = ressourceDao.findById(id).orElse(null);
            if (ressource != null) {
                commentaire.setRessource(ressource);
                Commentaire savedCommentaire = commentaireDao.save(commentaire);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentaire);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Supprimer un commentaire
    @DeleteMapping("/ressources/{id}/commentaires/{comId}")
    public ResponseEntity<String> deleteCommentaire(@PathVariable(name = "id") Integer id, @PathVariable(name = "comId") Integer comId) {
        try {
            Optional<Commentaire> commentaireOptional = commentaireDao.findById(comId);
            if (commentaireOptional.isPresent()) {
                commentaireDao.deleteById(comId);
                return ResponseEntity.ok("Commentaire supprimé avec succès");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}