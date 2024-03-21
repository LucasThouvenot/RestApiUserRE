package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "favoris")
@Getter
@Setter
public class Favoris implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String label;

    @ManyToMany
    @JoinTable(
            name = "favoris_ressources",
            joinColumns = @JoinColumn(name = "fav_id"),
            inverseJoinColumns = @JoinColumn(name = "res_id")
    )
    private List<Ressource> ressources;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
//    @JoinColumn(name = "utilisateurId")
//    private User user;


    public List<Ressource> getRessources() {
        return ressources;
    }

    public void addRessources(Ressource ressource){
        this.ressources.add(ressource);
    }

    public void delRessource(Ressource ressource){
        this.ressources.remove(ressource);
    }
}
