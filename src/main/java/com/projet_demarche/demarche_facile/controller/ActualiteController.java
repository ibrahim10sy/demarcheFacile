package com.projet_demarche.demarche_facile.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet_demarche.demarche_facile.model.Actualite;
import com.projet_demarche.demarche_facile.repository.ActualiteRepository;
import com.projet_demarche.demarche_facile.service.ActualiteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("Actualite")
@CrossOrigin
public class ActualiteController {
    @Autowired
    ActualiteService actualiteService;

    @PostMapping("/create")
    @Operation(summary = "Création d'un actualité")
    public ResponseEntity<Actualite> createActualite(
            @Valid @RequestParam("actualite") String actualiteString,
             @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws Exception {

        Actualite actualite = new Actualite();
        try{
            actualite = new JsonMapper().readValue(actualiteString , Actualite.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Actualite saveActualite = actualiteService.createActualite(actualite,multipartFile);
        return new ResponseEntity<>(saveActualite, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Mise à jours d'une actualite")
    public ResponseEntity<Actualite> updateActualite(@PathVariable long id,
    @Valid @RequestParam("actualite") String actualiteString,
    @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws Exception {

        Actualite actualite = new Actualite();
        try{
            actualite = new JsonMapper().readValue(actualiteString , Actualite.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Actualite updateActualite = actualiteService.updateActualite(actualite,id,multipartFile);
        return new ResponseEntity<>(updateActualite, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    @Operation(summary = "Affichage des actualites")
    public ResponseEntity<List<Actualite>> getAllActualite(){
        return new ResponseEntity<>(actualiteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/liste/{idAdmin}")
    @Operation(summary = "Liste des actualites créer par un admin")
    public ResponseEntity<List<Actualite>> getAllForAdmin(@PathVariable long idAdmin){
        return new ResponseEntity<>(actualiteService.getActualiteByIdAdmin(idAdmin), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Suppression des actualités")
    public ResponseEntity<String> deleteActualite(@PathVariable long id){
        return new ResponseEntity<>(actualiteService.delete(id), HttpStatus.OK);
    }
}
