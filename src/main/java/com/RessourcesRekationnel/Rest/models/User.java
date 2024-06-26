package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="utilisateur")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="nom_utilisateur",unique = true)
    private String pseudo;
    @Column(name="mot_de_passe")
    private String password;
    @Column(name="est_admin")
    private boolean admin;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="adresse_mail",unique = true)
    private String adresseMail;
    @Column(name="numero_telephone",unique = true)
    private String numeroTelephone;
    @Column(name = "est_actif")
    private Boolean actif;

    @Lob
    @Column(name="image_url", columnDefinition="LONGBLOB")
    private byte[] imageUrl;

    private String token;

    @OneToOne
    @JoinColumn(name = "favoris_id")
    private Favoris favoris;
//
//    @OneToMany
//    private List<Commentaire> commentaires;
}
