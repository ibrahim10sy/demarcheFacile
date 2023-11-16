package com.projet_demarche.demarche_facile.controller;

import com.projet_demarche.demarche_facile.model.Bureau;
import com.projet_demarche.demarche_facile.service.BureauService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bureau")
@CrossOrigin
public class BureauController {

    @Autowired
   private BureauService bureauService;

    @PostMapping("/create")
    @Operation(summary = "Création de bureau")
    public ResponseEntity<Bureau> createBureau(@Valid @RequestBody Bureau bureau){
        return new ResponseEntity<>(bureauService.createBureau(bureau), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "modifier un bureau")
    public ResponseEntity<Bureau> updateBureau(@Valid @RequestBody Bureau bureau, @PathVariable long id){
        return new ResponseEntity<>(bureauService.updateBureau(id,bureau), HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Liste des bureaux")
    public ResponseEntity<List<Bureau>> getAllBureau(){
        return new ResponseEntity<>(bureauService.getAllBureau(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    @Operation(summary = "Lire un seul bureau")
    public ResponseEntity<Bureau> getBureauById(@PathVariable long id){
        return new ResponseEntity<>(bureauService.getBureauByIdBureau(id), HttpStatus.OK);
    }

    @GetMapping("/bureaux")
    @Operation(summary = "rechercher les bureaux à travers idDocument")
    public ResponseEntity<List<Bureau>> getBureauxByIdDocumentAndNom(@RequestParam long idDocument, @RequestParam String nom) {
        return new ResponseEntity<>( bureauService.getBureauxByIdDocumentAndNom(idDocument, nom), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Suppression d'un bureau")
    public ResponseEntity<String> deleteBureau(@PathVariable long id){
        return new ResponseEntity<>(bureauService.deleteBureau(id), HttpStatus.OK);
    }
}
