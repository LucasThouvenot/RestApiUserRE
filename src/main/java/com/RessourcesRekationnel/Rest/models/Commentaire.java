package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date_publication")
    private Date datePubli;

    @Column(name="contenu")
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "utilisateurId")
//    private User user;
}
