package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.User;
import com.RessourcesRekationnel.Rest.security.JwtService;
import com.RessourcesRekationnel.Rest.security.MyUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationProvider authentication;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/sign-in")
    ResponseEntity<HttpStatus> signIn(@RequestBody User user) {

        if(userDao.findByPseudo(user.getPseudo()).isEmpty()){
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/login")
    ResponseEntity<User> login(@RequestBody User user) {

        try {
            Optional<User> optionalUser = userDao.findByPseudo(user.getPseudo());
            if(optionalUser.isPresent()){
                MyUserDetails userDetails = (MyUserDetails)authentication.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getPseudo(),
                                user.getPassword()
                        )
                ).getPrincipal();

                User currentUser = optionalUser.get();

                String jwt = jwtService.getJwtFromUser(userDetails);

                currentUser.setToken(jwt);
                userDao.save(currentUser);

                return new ResponseEntity<>(currentUser,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
            }


        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("users/{id}/profil-image")
    public ResponseEntity<User> uploadProfileImage(@PathVariable(name = "id") Integer id, @RequestParam("image") MultipartFile image) throws IOException {
        try{
            Optional<User> oUser = userDao.findById(id);
            if(oUser.isPresent()){

                User user = oUser.get();
                user.setImageUrl(image.getBytes());
                userDao.save(user);
                return new ResponseEntity<>(user,HttpStatus.OK);


            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<User> setUser(@PathVariable(name="id")Integer id,@RequestBody User sentUser){
        try{
            User user = userDao.findById(id).orElse(null);
            if(user != null){
                if(Objects.equals(user.getId(), sentUser.getId())){
                    user.setPseudo(sentUser.getPseudo() == null ? user.getPseudo() : sentUser.getPseudo());
                    if (sentUser.getPassword() != null && !Objects.equals(sentUser.getPassword(), user.getPassword())) {
                        user.setPassword(encoder.encode(sentUser.getPassword()));
                    }
                    user.setAdmin(sentUser.isAdmin() == user.isAdmin()? user.isAdmin() : sentUser.isAdmin());
                    user.setNom(sentUser.getNom() == null ? user.getNom() : sentUser.getNom());
                    user.setPrenom(sentUser.getPrenom() == null ? user.getPrenom() : sentUser.getPrenom());
                    user.setAdresseMail(sentUser.getAdresseMail() == null ? user.getAdresseMail() : sentUser.getAdresseMail());
                    user.setNumeroTelephone(sentUser.getNumeroTelephone() == null ? user.getNumeroTelephone() : sentUser.getNumeroTelephone());
                    user.setActif(sentUser.getActif() == null ? user.getActif() : sentUser.getActif());
                    user.setImageUrl(sentUser.getImageUrl() == null ? user.getImageUrl() : sentUser.getImageUrl());

                    userDao.save(user);
                }
                return new ResponseEntity<>(userDao.findById(sentUser.getId()).orElse(null),HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            System.out.println(new Date() + " ---- " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Récupérer tous les utilisateurs
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userDao.findAll();
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Aucun utilisateur trouvé
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Erreur interne du serveur
        }
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Integer id) {
        try {
            Optional<User> userOptional = userDao.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Erreur interne du serveur
        }
    }

//    @GetMapping("/users/{username}")
//    public ResponseEntity<Optional<User>> getUser(@RequestHeader String username){
//    }

}
