package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;

@Entity
public class Favoris {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

//    @OneToOne
//    @JoinColumn(name = "utilisateurId")
//    private User user;

}
