package com.projet_demarche.demarche_facile.controller;

import com.projet_demarche.demarche_facile.model.Forum;
import com.projet_demarche.demarche_facile.service.ForumService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Forum")
@CrossOrigin
public class ForumController {

    @Autowired
    ForumService forumService;

    @PostMapping("/createForAdmin")
    @Operation(summary = "Forum créer par admin")
    public ResponseEntity<Forum> createForAdmin(@Valid @RequestBody Forum forum){
        return new ResponseEntity<>(forumService.createForumAdmin(forum), HttpStatus.CREATED );
    }

    @PostMapping("/createForUser")
    @Operation(summary = "Forum créer par user")
    public ResponseEntity<Forum> createForUtilisateur(@Valid @RequestBody Forum forum){
        return new ResponseEntity<>(forumService.createForumUtilisateur(forum), HttpStatus.CREATED );
    }

    @PutMapping("/update/{idForum}/admin")
    @Operation(summary = "Modifier un forum créer par admin")
    public ResponseEntity<Forum> updateForAdmin(@Valid @RequestBody Forum forum,@PathVariable long idForum, @PathVariable long idAdmin){
        return new ResponseEntity<>(forumService.updateForumAdmin(forum,idForum, idAdmin), HttpStatus.OK );
    }

    @PutMapping("/update/{idForum}/utilisateur/{idUtilisateur}")
    @Operation(summary = "modifier  par user")
    public ResponseEntity<Forum> updateForUtilisateur(@Valid @RequestBody Forum forum,@PathVariable long idForum, @PathVariable long idUtilisateur){
        return new ResponseEntity<>(forumService.updateForumUtilisateur(forum,idForum,idUtilisateur), HttpStatus.OK );
    }

    @GetMapping("/read")
    @Operation(summary = "Affichage de tout les forum")
    public ResponseEntity<List<Forum>> getAll(){
        return new ResponseEntity<>(forumService.getAllForum(), HttpStatus.OK);
    }

    @GetMapping("/readForAdmin/{idAdmin}")
    @Operation(summary = "Forum d'un admin")
    public ResponseEntity<List<Forum>> getAllForAdmin(@PathVariable long idAdmin){
     return new ResponseEntity<>(forumService.getAllForumAdmin(idAdmin), HttpStatus.OK);

    }
    @GetMapping("/readForUser/{idUtilisateur}")
    @Operation(summary = "Forum d'un utilisateur")
    public ResponseEntity<List<Forum>> getAllForUtilisateur(@PathVariable long idUtilisateur){

     return new ResponseEntity<>(forumService.getAllForumUtilisateur(idUtilisateur),HttpStatus.OK);

    }
    @DeleteMapping("/deleteForAdmin/{idForum}/admin/{idAdmin}")
    public ResponseEntity<String> deleteForAdmin(@PathVariable long idForum, @PathVariable long idAdmin) {
        return new ResponseEntity<>(forumService.deleteForUtilisateur(idForum, idAdmin), HttpStatus.OK);
    }
    @DeleteMapping("/deleteForUtilisateur/{idForum}/utilisateur/{idUtilisateur}")
    public ResponseEntity<String> deleteForumForUtilisateur(@PathVariable long idForum, @PathVariable long idUtilisateur) {
        return new ResponseEntity<>(forumService.deleteForUtilisateur(idForum, idUtilisateur), HttpStatus.OK);
    }
}
