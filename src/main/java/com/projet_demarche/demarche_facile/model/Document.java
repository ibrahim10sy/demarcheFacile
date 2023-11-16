package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocument;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String audio;

    @Column(nullable = true)
    private String fichier;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="idGuide")
    private Guide guide;

    @ManyToOne
    @JoinColumn(name="idAdmin")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name="idBureau")
    private Bureau bureau;
   /* @ManyToMany
    @JoinTable(
            name = "bureau_document",
            joinColumns = @JoinColumn(name = "idDocument"),
            inverseJoinColumns = @JoinColumn(name = "idBureau")
    )
    @JsonIgnore
    private List<Bureau> bureaux;*/

}
