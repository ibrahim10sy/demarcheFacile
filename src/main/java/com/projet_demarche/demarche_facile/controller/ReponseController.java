package com.projet_demarche.demarche_facile.controller;

import com.projet_demarche.demarche_facile.model.Forum;
import com.projet_demarche.demarche_facile.model.Reponse;
import com.projet_demarche.demarche_facile.service.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reponse")
@CrossOrigin
public class ReponseController {

    @Autowired
    ReponseService reponseService;

    @PostMapping("/createForAdmin")
    @Operation(summary = "Reponse crée par Admin")
    public ResponseEntity<Reponse> createForAdmin(@Valid @RequestBody Reponse reponse){
        return new ResponseEntity<>(reponseService.reponseForAdmin(reponse), HttpStatus.OK);
    }

    @PostMapping("/createForUtilisateur")
    @Operation(summary = "Reponse crée par utilisateur")
    public ResponseEntity<Reponse> createForUtilisateur(@Valid @RequestBody Reponse reponse){
        return new ResponseEntity<>(reponseService.reponseForUtilisateur(reponse), HttpStatus.OK);
    }

    @PutMapping("/update/{idReponse}/utilisateur/{idUtilisateur}")
    @Operation(summary = "Mise à jours du reponse par utiliateur")
    public ResponseEntity<Reponse> updateForUtilisateur(@Valid Reponse reponse, @PathVariable long id, @PathVariable long idUtilisateur){
        return new ResponseEntity<>(reponseService.updateReponseForUtilisateur(reponse,id,idUtilisateur), HttpStatus.OK);
    }

    @PutMapping("/update/{idReponse}/admin/{idAdmin}")
    @Operation(summary = "Mise à jours du reponse par admin")
    public ResponseEntity<Reponse> updateForAdmin(@Valid Reponse reponse, @PathVariable long id, @PathVariable long idAdmin){
        return new ResponseEntity<>(reponseService.updateReponseForUtilisateur(reponse,id,idAdmin), HttpStatus.OK);
    }

    @GetMapping("/readForAdmin/{idAdmin}")
    @Operation(summary = "Affichage des reponses d'un admin")
    public ResponseEntity<List<Reponse>> getAllForAdmin(@PathVariable long idAdmin){
        return new ResponseEntity<>(reponseService.getReponseForAdmin(idAdmin), HttpStatus.OK);
    }

    @GetMapping("/readForUtilisateur/{idUtilisateur}/{idForum}")
    @Operation(summary = "Affichage des réponses d'un utilisateur pour un forum spécifique")
    public ResponseEntity<List<Reponse>> getAllForUtilisateur(@PathVariable long idUtilisateur, @PathVariable long idForum) {
        List<Reponse> reponseList = reponseService.getReponseForUtilisateur(idUtilisateur, idForum);
        return new ResponseEntity<>(reponseList, HttpStatus.OK);
    }
    @GetMapping("/readForOtherUtilisateurs/{idUtilisateur}/{idForum}")
    @Operation(summary = "Affichage des réponses des autres utilisateurs pour un forum spécifique")
    public ResponseEntity<List<Reponse>> getAllForOtherUtilisateurs(@PathVariable long idUtilisateur, @PathVariable long idForum) {
        List<Reponse> reponseList = reponseService.getAllReponseForOtherUtilisateurs(idUtilisateur, idForum);
        return new ResponseEntity<>(reponseList, HttpStatus.OK);
    }
    @DeleteMapping("/deleteForAdmin/{idReponse}/admin/{idAdmin}")
    @Operation(summary = "Suppression d'un reponse par admin")
    public ResponseEntity<String> deleteReponseForAdmin(@PathVariable long idReponse){
        return new ResponseEntity<>(reponseService.deleteForAdmin(idReponse), HttpStatus.OK);
    }

    @DeleteMapping("/deleteForUtilisateur/{idReponse}/utilisateur/{idUtilisateur}")
    @Operation(summary = "Suppression d'un reponse par utilisateur")
    public ResponseEntity<String> deleteReponseForUtilisateur(@PathVariable long idReponse){
        return new ResponseEntity<>(reponseService.deleteForUtilisateur(idReponse), HttpStatus.OK);
    }
}
