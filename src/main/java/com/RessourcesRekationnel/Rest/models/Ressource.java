package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ressource")
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String label;

//    @OneToMany
//    private List<Commentaire> commentaires;

}
