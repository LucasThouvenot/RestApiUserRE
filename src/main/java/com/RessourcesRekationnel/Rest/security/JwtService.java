package com.RessourcesRekationnel.Rest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String getJwtFromUser(MyUserDetails userDetails) {


        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256,"secret_du_serveur")
                .compact();

    }

    public String getPseudoFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("secret_du_serveur")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}
