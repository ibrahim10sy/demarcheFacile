package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.model.*;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import com.projet_demarche.demarche_facile.repository.ForumRepository;
import com.projet_demarche.demarche_facile.repository.UtilisateurRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    @Autowired
    ForumRepository forumRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    AdminRepository adminRepository;

    public Forum createForumAdmin(Forum forum){
        Admin admin = adminRepository.findByIdAdmin(forum.getAdmin().getIdAdmin());
        if(admin == null)
            throw new IllegalArgumentException("Admin non trouvé avec l'ID spécifié");
        return forumRepository.save(forum);
    }
    public Forum createForumUtilisateur(Forum forum){
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(forum.getUtilisateur().getIdUtilisateur());
        if (utilisateur == null)
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID spécifié");
        System.out.println(forum);
        return forumRepository.save(forum);
    }

    public Forum updateForumAdmin(Forum forum, long id, long idAdmin){
        Forum forum1 = forumRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Forum non trouvé"));
        if (forum1 == null) {
            throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
        }

        Admin admin = adminRepository.findByIdAdmin(idAdmin);
        if(admin == null)
            throw new IllegalArgumentException("Admin non trouvé avec l'ID spécifié");

        if (!forum1.getAdmin().equals(admin)) {
            throw new IllegalArgumentException("Vous n'êtes pas autorisé à modifier ce forum");
        }
        forum1.setDescription(forum.getDescription());
        forum1.setLibelle(forum.getLibelle());
        return forumRepository.save(forum1);
    }

    public Forum updateForumUtilisateur(Forum forum, long id, long idUtilisateur){
        Forum forum1 = forumRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Forum non trouvé"));
        if (forum1 == null) {
            throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
        }

        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(idUtilisateur);
        if(utilisateur == null)
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID spécifié");

        if (!forum1.getUtilisateur().equals(utilisateur)) {
            throw new IllegalArgumentException("Vous n'êtes pas autorisé à modifier ce forum");
        }
        forum1.setDescription(forum.getDescription());
        forum1.setLibelle(forum.getLibelle());
        return forumRepository.save(forum1);
    }
   /* public List<Forum> getAllForumUtilisateur(long idUtilisateur){
        List<Forum> forumList = forumRepository.getAllForumByUtilisateur(idUtilisateur);
        if(forumList.isEmpty())
            throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
        return forumList;
    }*/
   public List<Forum> getAllForumAdmin(long idAdmin) {
       List<Forum> forumList = forumRepository.getAllForumByAdmin_IdAdmin(idAdmin);
       if (forumList.isEmpty()) {
           throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
       }
       return forumList;
   }
    public List<Forum> getAllForumUtilisateur(long idUtilisateur) {
        List<Forum> forumList = forumRepository.getAllForumByUtilisateur_IdUtilisateur(idUtilisateur);
        if (forumList.isEmpty()) {
            throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
        }
        return forumList;
    }
    /*public List<Forum> getAllForumAdmin(long idAdmin){
        List<Forum> forumList = forumRepository.getAllForumByAdmin(idAdmin);
        if(forumList.isEmpty())
            throw new IllegalArgumentException("Forum non trouvé avec l'ID spécifié");
        return forumList;
    }
*/
    public List<Forum> getAllForum(){
        List<Forum> forumList = forumRepository.findAll();
        return forumList;
    }
    public String deleteForAdmin(long id){
       Forum forum = forumRepository.getByIdForum(id);
        if(forum == null)
            throw new EntityExistsException("Impossible de supprimé car aucun forum trouvé");

        Admin admin = adminRepository.findByIdAdmin(forum.getAdmin().getIdAdmin());
        if(admin == null)
            throw new IllegalArgumentException("Admin non trouvé avec l'ID spécifié");

        if (!forum.getAdmin().equals(admin)) {
            throw new IllegalArgumentException("Vous n'êtes pas autorisé à modifier ce forum");
        }
        forumRepository.deleteById(id);
        return "Supprimé avec succèss";
    }

    public String deleteForUtilisateur(long id){
        Forum forum = forumRepository.getByIdForum(id);
        if(forum == null)
            throw new EntityExistsException("Impossible de supprimé car aucun forum trouvé");

        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateur(forum.getUtilisateur().getIdUtilisateur());
        if(utilisateur == null)
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID spécifié");

        if (!forum.getUtilisateur().equals(utilisateur)) {
            throw new IllegalArgumentException("Vous n'êtes pas autorisé à modifier ce forum");
        }
        forumRepository.deleteById(id);
        return "Supprimé avec succèss";
    }
}
