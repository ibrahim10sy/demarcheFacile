package com.projet_demarche.demarche_facile.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet_demarche.demarche_facile.model.Actualite;
import com.projet_demarche.demarche_facile.model.Bureau;
import com.projet_demarche.demarche_facile.model.Document;
import com.projet_demarche.demarche_facile.model.Guide;
import com.projet_demarche.demarche_facile.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("Document")
@CrossOrigin
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/create")
    @Operation(summary = "création d'un document")
    public ResponseEntity<Document> create(
            @Valid @RequestParam("document") String documentString,
            @RequestParam(value = "image", required = false) MultipartFile multipartFileImage,
            @RequestParam(value = "audio", required = false)MultipartFile multipartFileAudio,
            @RequestParam(value = "fichier", required = false)MultipartFile multipartFileFichier
          ) throws Exception {
        Document document = new Document();
        try {
            document = new JsonMapper().readValue(documentString, Document.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        Document saveDocument = documentService.createDocument(document, multipartFileImage, multipartFileAudio, multipartFileFichier);
       return new ResponseEntity<>(saveDocument, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "mise à jour d'un document")
    public ResponseEntity<Document> update(
            @PathVariable long id,
            @Valid @RequestParam("document") String documentString,
            @RequestParam(value = "image", required = false) MultipartFile multipartFileImage,
            @RequestParam(value = "audio", required = false)MultipartFile multipartFileAudio,
            @RequestParam(value = "fichier", required = false)MultipartFile multipartFileFichier) throws Exception {
        Document document = new Document();
        try {
            document = new JsonMapper().readValue(documentString, Document.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        Document saveDocument = documentService.updateDocument(document,id, multipartFileImage, multipartFileAudio, multipartFileFichier);
        return new ResponseEntity<>(saveDocument, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    @Operation(summary = "affiche des documents")
    public ResponseEntity<List<Document>> getAllDocument(){
        return new ResponseEntity<>(documentService.getAllDocument(), HttpStatus.OK);
    }

    @GetMapping("/liste/{idGuide}")
    @Operation(summary = "Les documents associées à un guide")
    public ResponseEntity<List<Document >> getAllDocument(@PathVariable long idGuide){
        return new ResponseEntity<>(documentService.findDocumentByIdGuide(idGuide), HttpStatus.OK);
    }
    @GetMapping("read/{id}")
    @Operation(summary = "Affichage d'un seul document")
    public ResponseEntity<Document> getDocumentById(@PathVariable long id){
        return new ResponseEntity<>(documentService.getDocumentById(id), HttpStatus.OK);
    }

   /* @GetMapping("/guide")
    @Operation(summary = "Affichage d'un seul guide")
    public ResponseEntity<Document> getGuideByDocument(@RequestBody Document document){
        return new ResponseEntity<>(documentService.getAllDocumentByGuide(document),
                HttpStatus.OK);
    }*/
   @GetMapping("/{nomDocument}/guides")
   @Operation(summary = "Affichage des guides à travers le nom du document")
   public List<Guide> getGuidesByDocument(@PathVariable String nomDocument) {
       return documentService.findGuidesByDocument(nomDocument);
   }
    @GetMapping("/{nomDocument}/bureaux")
    @Operation(summary = "Affichage des bureaux à travers le nom du document")
    public List<Bureau> getBureauxByDocumentName(@PathVariable String nomDocument) {
        return documentService.findBureauxByNameDocument(nomDocument);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimé un guide")
    public ResponseEntity<String> deleteDocument( @PathVariable long id){
        return new ResponseEntity<>(documentService.delete(id), HttpStatus.OK);
    }
}
