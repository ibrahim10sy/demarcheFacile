package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.Exception.NoContentException;
import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Bureau;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import com.projet_demarche.demarche_facile.repository.BureauRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BureauService {

    @Autowired
    private BureauRepository bureauRepository;
    @Autowired
    private AdminRepository adminRepository;

    public Bureau createBureau(Bureau bureau){
        Admin admin = adminRepository.findByIdAdmin(bureau.getAdmin().getIdAdmin());
        if(admin == null)
            throw new EntityExistsException("Compte non trouvé");

      return bureauRepository.save(bureau);
    }

    public Bureau updateBureau(long id, Bureau bureau){
        Bureau bureau1 = bureauRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Bureau introuvabke avec ID :"+id));
        bureau1.setNom(bureau.getNom());
        bureau1.setVille(bureau.getVille());
        bureau1.setAdresse(bureau.getAdresse());
        bureau1.setLatitude(bureau.getLatitude());
        bureau1.setLongitude(bureau.getLongitude());

        return bureauRepository.save(bureau1);
    }

    public List<Bureau> getAllBureau(){
        List<Bureau> bureaus = bureauRepository.findAll();
        if(bureaus.isEmpty())
            throw new NoContentException("Aucun bureau trouvé");
        return bureaus;
    }

    public Bureau getBureauByIdBureau(long idBureau){
        Bureau bureau = bureauRepository.findByIdBureau(idBureau);
        if(bureau == null)
            throw new EntityNotFoundException("cet bureau n'existe pas");
        return bureau;
    }

    //Rechercher les bureaux à travers id du document ou le nom
    public List<Bureau> getBureauxByIdDocumentAndNom(long idDocument, String nom) {
        return bureauRepository.findByIdDocumentAndNom(idDocument, nom);
    }
    public String deleteBureau(long id){
        Bureau bureau1 = bureauRepository.findByIdBureau(id);
        if(bureau1 == null)
            throw new EntityNotFoundException("Impossible de supprimé le bureau");
        bureauRepository.delete(bureau1);
        return "Supprimé avec succèss";
    }

}
