package com.projet_demarche.demarche_facile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet_demarche.demarche_facile.model.Utilisateur;
import com.projet_demarche.demarche_facile.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("utilisateur")
public class utilisateurController {

    private UtilisateurService utilisateurService;

    @PostMapping("/create")
    @Operation(summary = "Création de compte utilisateur")
    public ResponseEntity<Utilisateur> createUtilisateur(@Valid @RequestParam("utilisateur") String utilisateurString,
                                                         @RequestParam(value = "image", required = false)MultipartFile multipartFile) throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        try{
            utilisateur = new JsonMapper().readValue(utilisateurString,Utilisateur.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Utilisateur saveUser = utilisateurService.createUser(utilisateur,multipartFile);
        return  new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Mise à jour d'un utilisateur")
    public ResponseEntity<Utilisateur> updateUser( @PathVariable Long id,@Valid @RequestParam("utilisateur") String utilisateurString,
     @RequestParam(value ="image", required=false) MultipartFile multipartFile) throws Exception{
        Utilisateur utilisateur = new Utilisateur();
        try {
            utilisateur = new JsonMapper().readValue(utilisateurString, Utilisateur.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        Utilisateur updateUser = utilisateurService.updateUtilisateur(utilisateur, id, multipartFile);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/read")
    @Operation(summary = "Affichages utilisateurs")
    public  ResponseEntity<List<Utilisateur>> getUtilisateur(){
        return  new ResponseEntity<>(utilisateurService.getAllUtisateur(), HttpStatus.OK);
    }
    @GetMapping("/read/{id}")
    @Operation(summary = "Affichage d'un utilisateurs")
    public ResponseEntity<Utilisateur> getUtilisateurById(@Valid @PathVariable long id){
        return new ResponseEntity<>(utilisateurService.getUtilisateurById(id),HttpStatus.OK) ;
    }
    @DeleteMapping("/delete")
    @Operation(summary = "Suppression d'un utilisateur")
    public ResponseEntity<String> deleteUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        return new ResponseEntity<>(utilisateurService.deleteUserById(utilisateur), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur")
    public Object connexion(@RequestParam("email") String email, @RequestParam("mdp") String mdp){
        return utilisateurService.connexion(email, mdp);
    }
}
