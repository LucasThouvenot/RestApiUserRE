package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;

@Entity
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String label;
}
