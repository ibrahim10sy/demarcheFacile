package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
    public Admin findByEmailAndMotDePasse(String email, String mdp);

    public  Admin findByEmail(String email);
    public Admin findByIdAdmin(long id);
}
