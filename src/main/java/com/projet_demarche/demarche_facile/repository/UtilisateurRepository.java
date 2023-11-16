package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    public Utilisateur findByEmailAndMotDePasse(String email, String motDePsse);

    public  Utilisateur findByEmail(String email);

    public  Utilisateur findByIdUtilisateur(long id);
}
