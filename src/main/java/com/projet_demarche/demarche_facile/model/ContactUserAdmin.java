package com.projet_demarche.demarche_facile.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_user_admin")
public class ContactUserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContact;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String message;

}
