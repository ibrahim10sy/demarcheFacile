package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

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


    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;

    @OneToMany(mappedBy = "forum")
    @JsonIgnore
    private List<Reponse> reponses;
}
