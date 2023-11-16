package com.projet_demarche.demarche_facile.repository;

import com.projet_demarche.demarche_facile.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum,Long> {
    Forum getByIdForum(long id);
  //  List<Forum> getAllForumByUtilisateur(long idUtilisateur);
  //  List<Forum> getAllForumByAdmin(long idAdmin);
    //List<Forum> getAllForumByUtilisateurs(long idUtilisateur);
  List<Forum> getAllForumByAdmin_IdAdmin(long idAdmin);
    List<Forum> getAllForumByUtilisateur_IdUtilisateur(long idUtilisateur);

}
