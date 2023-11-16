package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Forum;
import com.projet_demarche.demarche_facile.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {

    Reponse findReponseByIdReponse(long idReponse);
    List<Reponse> getAllReponseByAdmin_IdAdmin(long idAdmin);
    List<Reponse> getAllReponseByUtilisateur_IdUtilisateur(long idUtilisateur);

}
