package com.RessourcesRekationnel.Rest.controllers;

import com.RessourcesRekationnel.Rest.dao.RessourceDao;
import com.RessourcesRekationnel.Rest.models.Ressource;
import com.RessourcesRekationnel.Rest.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class RessourceController {

    @Autowired
    private RessourceDao ressourceDao;

    @Autowired
    private QueryUtils queryUtils;

//    public ResponseEntity<Ressource> getRecentRessources(Integer id){
//
//    }

    @GetMapping("/ressources")
    public ResponseEntity<List<Ressource>> getAllRessouces(){
        return new ResponseEntity<>(ressourceDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ressources/{id}")
    public ResponseEntity<Ressource> getOneRessources(@PathVariable(name = "id") Integer id){
        if(ressourceDao.existsById(id)){
            return new ResponseEntity<>(ressourceDao.findById(id).orElse(new Ressource()),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Scheduled(fixedDelay = 600000)
    public void updateDatabaseFromApi() {
        try {
            String apiUrl = "http://localhost:8083"; // Replace with your actual API URL
            List<Ressource> resources =  queryUtils.getResourcesFromApi(apiUrl + "/ressources");

            // Save or update resources in the database
            for (Ressource resource : resources) {
                // Check if the resource exists in the database
                Ressource existingResource = ressourceDao.findById(resource.getId()).orElse(null);

                if (existingResource != null) {
                    // Update existing resource
                    existingResource.setTitre(resource.getTitre());
                    existingResource.setRestreinte(resource.getRestreinte());
                    existingResource.setExploite(resource.getExploite());
                    existingResource.setContenu(resource.getContenu());
                    // Update other fields as needed
                    ressourceDao.save(existingResource);
                } else {
                    // Save new resource
                    ressourceDao.save(resource);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
