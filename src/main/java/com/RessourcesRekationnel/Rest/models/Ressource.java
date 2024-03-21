package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ressource")
public class Ressource implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titre",length = 50)
    private String titre;

    @Column(name = "date_publication")
    private Date datePubli;

    @Column(name = "est_public")
    private Boolean estPublic;

    @Column(name = "exploite")
    private Boolean exploite;

    @Column(name = "content",length = 3000)
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
