package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Query("SELECT d.guide FROM Document d WHERE d.nom = :nomDocument")
    List<Guide> findGuidesByNomDocument(@Param("nomDocument") String nomDocument);
    Document findByIdDocument(long id);
    @Query("SELECT d.bureau FROM Document d WHERE d.nom = :nomDocument")
    List<Bureau> findBureauxByDocument(@Param("nomDocument") String nomDocument);

    List<Document> getAllDocumentByGuide_IdGuide(long idGuide);
    Document findByNom(String nom);
}
