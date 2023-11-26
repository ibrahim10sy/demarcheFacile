package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.ContactUserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactUserAdmin,Long> {

    List<ContactUserAdmin> findByUtilisateurIdUtilisateur(long idUtilisateur);

    List<ContactUserAdmin> findByAdminIdAdmin(long idAdmin);
}
