package com.projet_demarche.demarche_facile.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class ContactUserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContact;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;


}
