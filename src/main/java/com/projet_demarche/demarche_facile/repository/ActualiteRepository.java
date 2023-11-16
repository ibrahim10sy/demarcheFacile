package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Actualite;
import com.projet_demarche.demarche_facile.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActualiteRepository extends JpaRepository<Actualite,Long> {

 Actualite findByIdActualite(long id);
 // List<Actualite> findByIdAdmin(long idAdmin);
 List<Actualite> getAllActualiteByAdmin_IdAdmin(long idAdmin);

}
