package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtilisateur;

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

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Forum> forum;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<ContactUserAdmin> contactUserAdmins;
}
