package com.RessourcesRekationnel.Rest.utils;

import com.RessourcesRekationnel.Rest.controllers.UserController;
import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryUtils {

    @Autowired
    private UserDao userDao;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Ressource> getResourcesFromApi(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = response.getBody();
            return parseJson(jsonResponse);
        } else {
            throw new RuntimeException("Failed to retrieve resources from API: " + response.getStatusCodeValue());
        }
    }

    private List<Ressource> parseJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> data = mapper.readValue(json, List.class);
            List<Ressource> resources = new ArrayList<>();
            for (Map<String, Object> map : data) {
                Ressource resource = mapToRessource(map);
                resources.add(resource);
            }
            return resources;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON response: " + e.getMessage());
        }
    }

    private Ressource mapToRessource(Map<String, Object> data) {
        Ressource resource = new Ressource();
        resource.setId((Integer) data.get("id"));
        resource.setTitre((String) data.get("titre"));
        resource.setRestreinte((Boolean) data.get("restreinte"));
        resource.setExploite((Boolean) data.get("exploite"));
        resource.setContenu((String) data.get("contenu"));
        resource.setUser(userDao.findById((Integer) data.get("idUser") ).orElse(null));
        return resource;
    }

}
