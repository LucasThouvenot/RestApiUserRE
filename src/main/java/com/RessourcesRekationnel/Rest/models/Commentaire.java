package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.availability.AvailabilityChangeEvent;

import java.io.Serializable;
import java.sql.Date;

@Entity(name = "commentaire")
@Getter
@Setter
public class Commentaire implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="date_publication")
    private Date datePubli;

    @Column(name="contenu")
    private String content;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ressource_id",referencedColumnName = "id")
    private Ressource ressource;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }


}
