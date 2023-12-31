package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdmin;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    @Email(message = "Email incorrect !")
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Actualite> actualites;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Guide> guides;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Bureau> bureaux;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Forum> forums;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<ContactUserAdmin> contactUserAdmins;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Document> documents;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Reponse> reponses;
}
