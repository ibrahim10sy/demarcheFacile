package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Bureau;
import com.projet_demarche.demarche_facile.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BureauRepository extends JpaRepository<Bureau, Long> {

   // public Bureau findByDocuments(Document document);
    public Bureau findByIdBureau(long id);
    @Query("SELECT b FROM Bureau b JOIN b.documents d WHERE d.idDocument = :idDocument AND d.nom = :nom")
    List<Bureau> findByIdDocumentAndNom(long idDocument, String nom);
}
