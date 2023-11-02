package com.projet_demarche.demarche_facile.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    @Operation(summary = "Création de compte admin")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestParam("admin") String adminString,
   @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws Exception {

        Admin admin = new Admin();
        try{
            admin = new JsonMapper().readValue(adminString, Admin.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Admin saveAdmin = adminService.createAdmin(admin, multipartFile);
        return new ResponseEntity<>(saveAdmin,  HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Mise à jour")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id,@Valid @RequestParam("admin") String adminString,
                                             @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws Exception {
        Admin admin = new Admin();
        try{
            admin = new JsonMapper().readValue(adminString,Admin.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Admin updateAdmin = adminService.updateAdmin(admin,id,multipartFile);
        return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    @Operation(summary = "Affichage d'un admin")
    public ResponseEntity<Admin> getAdminById(@Valid @PathVariable long id){
        return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Suppression d'un admin")
    public ResponseEntity<String> deleteAdmin(@Valid @RequestBody Admin admin){
        return new ResponseEntity<>(adminService.deleteAdmin(admin), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion au compte admin")
    public Object connexion(@RequestParam("email") String email, @RequestParam("mdp") String mdp){
        return adminService.connexion(email,mdp);
    }

}
