package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReponse;

    @Column(nullable = false)
    private String reponse;

    @ManyToOne
    @JoinColumn(name = "idForum")
    private Forum forum;
}
