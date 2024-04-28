package com.RessourcesRekationnel.Rest.models;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentaireDTO {
        private int id;
        private Date datePubli;
        private String content;
        private UserDTO user;
        private RessourceDTO ressource;
}
