package com.RessourcesRekationnel.Rest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "image")
@Getter
@Setter
public class Images implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name="image", columnDefinition="LONGBLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "ressource_id",referencedColumnName = "id")
    private Ressource ressource;
}
