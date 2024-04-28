package com.RessourcesRekationnel.Rest.models;

import lombok.Data;

@Data
public class RessourceDTO {

    private Integer id;
    private String titre;
    private UserDTO user;
    private Boolean restreinte;

}
