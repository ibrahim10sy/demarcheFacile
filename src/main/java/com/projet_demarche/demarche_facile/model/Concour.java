package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Concour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConcour;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String description;

    @NotNull(message = "Désole la date ne doit pas etre null")
    @Column(nullable = false)
    private LocalDate dateDebut;


    @Column(nullable = false)
    private LocalDate dateFin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;
}
