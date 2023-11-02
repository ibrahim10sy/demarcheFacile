package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide,Long> {

    public Guide findByIdGuide(long id);
    public Guide findByLibelle(String libelle);
}
