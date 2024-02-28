package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "commentaire")
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

    @ManyToOne
    @JoinColumn(name = "ressource_id",referencedColumnName = "id")
    private Ressource ressource;
}
