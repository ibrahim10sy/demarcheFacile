package com.projet_demarche.demarche_facile.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet_demarche.demarche_facile.model.Guide;
import com.projet_demarche.demarche_facile.service.GuideService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Guide")
@CrossOrigin
public class GuideController {

    @Autowired
    GuideService guideService;

    @PostMapping("/create")
    @Operation(summary = "création d'un guide")
    public ResponseEntity<Guide> create(
            @Valid @RequestParam("guide") String guideString,
            @RequestParam(value = "image", required = false) MultipartFile multipartFileImage,
            @RequestParam(value = "audio", required = false)MultipartFile multipartFileAudio) throws Exception {
         Guide guide = new Guide();
        try{
            guide = new JsonMapper().readValue(guideString,Guide.class );
        }catch (Exception e){
            throw new Exception("Impossible de créer un nouveau guide");
        }
        Guide saveGuide = guideService.createGuide(guide,multipartFileImage,multipartFileAudio);
        return new ResponseEntity<>(saveGuide, HttpStatus.CREATED);
    }
  /* @PostMapping("/create")
   @Operation(summary = "création d'un guide")
   public ResponseEntity<Guide> create(
           @RequestPart("guide") Guide guide,
           @RequestPart(value = "image", required = false) MultipartFile multipartFileImage,
           @RequestPart(value = "audio", required = false) MultipartFile multipartFileAudio) throws Exception {
       try {
           Guide saveGuide = guideService.createGuide(guide, multipartFileImage, multipartFileAudio);
           return new ResponseEntity<>(saveGuide, HttpStatus.CREATED);
       } catch (EntityNotFoundException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       } catch (EntityExistsException e) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
*/
    @PutMapping("/update/{id}")
    @Operation(summary = "mise à jours d'un guide")
    public ResponseEntity<Guide> update(
            @PathVariable long id,
            @Valid @RequestParam("guide") String guideString,
            @RequestParam(value = "image", required = false) MultipartFile multipartFileImage,
            @RequestParam(value = "audio", required = false)MultipartFile multipartFileAudio) throws Exception {
        Guide guide = new Guide();
        try{
            guide = new JsonMapper().readValue(guideString,Guide.class );
        }catch (Exception e){
            throw new Exception("Impossible de mettre à jour ce guide");
        }
        Guide updateGuide = guideService.updateGuide(guide,id,multipartFileImage,multipartFileAudio);
        return new ResponseEntity<>(updateGuide, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    @Operation(summary = "affiche des guides")
    public ResponseEntity<List<Guide>> getAllGuide(){
        return new ResponseEntity<>(guideService.getAllGuide(), HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    @Operation(summary = "Affichage d'un seul guide")
    public ResponseEntity<Guide> getGuideById(@PathVariable long id){
        return new ResponseEntity<>(guideService.getGuideById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimé un guide")
    public ResponseEntity<String> deleteGuide( @PathVariable long id){
        return new ResponseEntity<>(guideService.delete(id), HttpStatus.OK);
    }
}
