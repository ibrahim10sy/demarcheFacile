package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Actualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idActualite;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String description;

    @NotNull(message = "Désole la date ne doit pas etre null")
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;


    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;
}
