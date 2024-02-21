package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Favoris {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "favoris_ressources",
            joinColumns = @JoinColumn(name = "fav_id"),
            inverseJoinColumns = @JoinColumn(name = "res_id")
    )
    private List<Ressource> ressources;

//    @OneToOne
//    @JoinColumn(name = "utilisateurId")
//    private User user;

}
