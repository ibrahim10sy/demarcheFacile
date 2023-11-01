package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {
}
