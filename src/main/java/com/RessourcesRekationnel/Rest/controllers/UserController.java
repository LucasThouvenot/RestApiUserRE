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
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userDao.findAll(),HttpStatus.OK);
    }

//    @GetMapping("/users/{username}")
//    public ResponseEntity<Optional<User>> getUser(@RequestHeader String username){
//    }

}
