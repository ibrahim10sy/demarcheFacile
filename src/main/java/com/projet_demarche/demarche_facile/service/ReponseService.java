package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Forum;
import com.projet_demarche.demarche_facile.model.Reponse;
import com.projet_demarche.demarche_facile.model.Utilisateur;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import com.projet_demarche.demarche_facile.repository.ForumRepository;
import com.projet_demarche.demarche_facile.repository.ReponseRepository;
import com.projet_demarche.demarche_facile.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseService {

    @Autowired
    ReponseRepository reponseRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ForumRepository forumRepository;

    public Reponse reponseForAdmin(Reponse reponse){
        Admin admin = adminRepository.findByIdAdmin(reponse.getAdmin().getIdAdmin());
        if(admin == null)
            throw new IllegalArgumentException("Admin non trouvé avec ID spécifié"+reponse.getAdmin().getIdAdmin());
        Forum forum = forumRepository.getByIdForum(reponse.getForum().getIdForum());
        if(forum == null)
            throw  new IllegalArgumentException("Forum non trouvé");
        return reponseRepository.save(reponse);
    }

    public Reponse reponseForUtilisateur(Reponse reponse) {
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(reponse.getUtilisateur().getIdUtilisateur());
        if (utilisateur == null) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID spécifié");
        }

        Forum forum = forumRepository.getByIdForum(reponse.getForum().getIdForum());
        if (forum == null) {
            throw new EntityNotFoundException("Forum non trouvé avec l'ID spécifié");
        }

        // Associer la réponse à l'utilisateur et au forum
        //reponse.setUtilisateur(utilisateur);
        //reponse.setForum(forum);

        return reponseRepository.save(reponse);
    }

    public Reponse updateReponseForAdmin(Reponse reponse , long id, long idAdmin){
        Reponse reponse1 = reponseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Non trouvé"));
        Admin admin = adminRepository.findByIdAdmin(idAdmin);
        if(admin == null)
            throw new EntityNotFoundException("Admin non trouvé");
        reponse1.setReponse(reponse.getReponse());
        return reponseRepository.save(reponse1);
    }

    public Reponse updateReponseForUtilisateur(Reponse reponse , long idUtilisateur, long id){
        Reponse reponse1 = reponseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Non trouvé"));
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(idUtilisateur);
        if (utilisateur == null)
            throw new EntityNotFoundException("Utilisateur non trouvé");
        reponse1.setReponse(reponse.getReponse());
        return reponseRepository.save(reponse1);
    }

    public List<Reponse> getReponseForUtilisateur(long idUtilisateur, long idForum) {
        List<Reponse> reponseList = reponseRepository.getAllReponseByUtilisateur_IdUtilisateurAndForum_IdForum(idUtilisateur, idForum);
        if (reponseList.isEmpty())
            throw new EntityNotFoundException("Aucune réponse trouvée");
        return reponseList;
    }

    public List<Reponse> getAllReponseForOtherUtilisateurs(long idUtilisateur, long idForum) {
        List<Reponse> reponseList = reponseRepository.getAllReponseByUtilisateur_IdUtilisateurNotAndForum_IdForum(idUtilisateur, idForum);
        if (reponseList.isEmpty())
            throw new EntityNotFoundException("Aucune réponse trouvée pour d'autres utilisateurs");
        return reponseList;
    }

    public List<Reponse> getReponseForAdmin(long idAdmin){
        List<Reponse> reponseList = reponseRepository.getAllReponseByAdmin_IdAdmin(idAdmin);
        if(reponseList.isEmpty())
            throw new EntityNotFoundException("Aucune reponse trouvé");
        return reponseList;
    }

    public String deleteForUtilisateur(long id){
        Reponse reponse = reponseRepository.findReponseByIdReponse(id);
        if(reponse == null)
            throw  new EntityNotFoundException("Reponse non trouvé");
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(reponse.getUtilisateur().getIdUtilisateur());
        if(utilisateur == null)
            throw new EntityNotFoundException("Utilisateur non trouvé");
        reponseRepository.deleteById(id);
        return "Supprimé avec succèss";
    }
    public String deleteForAdmin(long id){
        Reponse reponse = reponseRepository.findReponseByIdReponse(id);
        if(reponse == null)
            throw  new EntityNotFoundException("Reponse non trouvé");
            Admin admin = adminRepository.findByIdAdmin(reponse.getAdmin().getIdAdmin());
        if(admin == null)
            throw new EntityNotFoundException("Utilisateur non trouvé");
        reponseRepository.deleteById(id);
        return "Supprimé avec succèss";
    }
}
