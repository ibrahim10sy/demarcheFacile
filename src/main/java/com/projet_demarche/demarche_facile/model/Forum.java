package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Forum {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idForum;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
}
