package com.RessourcesRekationnel.Rest.utils;


import org.springframework.web.client.RestTemplate;

public class Rest {

    private RestTemplate restTemplate = new RestTemplate();
    Rest(){}

    public RestTemplate getRestTemplate(){
        return this.restTemplate;
    }
}
