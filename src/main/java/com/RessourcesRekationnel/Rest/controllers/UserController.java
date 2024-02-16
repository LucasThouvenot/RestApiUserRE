package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.User;
import com.RessourcesRekationnel.Rest.security.JwtService;
import com.RessourcesRekationnel.Rest.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<User> signIn(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody User user) {

        try {
            MyUserDetails userDetails = (MyUserDetails)authentication.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getPseudo(),
                            user.getPassword())
            ).getPrincipal();

            String jwt = jwtService.getJwtFromUser(userDetails);

            return new ResponseEntity<>(jwt,HttpStatus.OK);

        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }

}
