package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGuide;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String audio;

    @Column(nullable = false)
    private String libelle;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;
}
