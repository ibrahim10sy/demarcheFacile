package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.model.Actualite;
import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.repository.ActualiteRepository;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ActualiteService {
    @Autowired
    ActualiteRepository actualiteRepository;
    @Autowired
    AdminRepository adminRepository;
    public Actualite createActualite(Actualite actualite, MultipartFile multipartFile) throws Exception {
        Admin admin = adminRepository.findByIdAdmin(actualite.getAdmin().getIdAdmin());

        if(admin == null)
            throw new IllegalArgumentException("Admin non trouvé");
        if(multipartFile != null){
            String location = "C:\\xampp\\htdocs\\demarche";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(),
                            rootlocation.resolve(multipartFile.getOriginalFilename()));
                    actualite.setImage("http://localhost:8080/demarche"
                            +multipartFile.getOriginalFilename());
                }else{
                    try {
                        String nom = location+"\\"+multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFile.getInputStream(),
                                    rootlocation.resolve(multipartFile.getOriginalFilename()));
                            actualite.setImage("http://localhost:8080/demarche"
                                    +multipartFile.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                            actualite.setImage("http://localhost:8080/demarche"
                                    +multipartFile.getOriginalFilename());
                        }
                    }catch (Exception e){
                        throw new Exception("Impossible de télécharger l\'image");
                    }
                }
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
    }
        return actualiteRepository.save(actualite);
    }

    public Actualite updateActualite(Actualite actualite, long id, MultipartFile multipartFile) throws Exception {
        Actualite actualite1 = actualiteRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Aucun actualité ne correspond à ID" + id));
        actualite1.setLibelle(actualite.getLibelle());
        actualite1.setDescription(actualite.getDescription());
        actualite1.setDateDebut(actualite.getDateDebut());
        actualite1.setDateFin(actualite.getDateFin());

        if(multipartFile != null){
            String location = "C:\\xampp\\htdocs\\demarche";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(),
                            rootlocation.resolve(multipartFile.getOriginalFilename()));
                    actualite1.setImage("http://localhost:8080/demarche"
                            +multipartFile.getOriginalFilename());
                }else{
                    try {
                        String nom = location+"\\"+multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFile.getInputStream(),
                                    rootlocation.resolve(multipartFile.getOriginalFilename()));
                            actualite1.setImage("http://localhost:8080/demarche"
                                    +multipartFile.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                            actualite1.setImage("http://localhost:8080/demarche"
                                    +multipartFile.getOriginalFilename());
                        }
                    }catch (Exception e){
                        throw new Exception("Impossible de télécharger l\'image");
                    }
                }
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
        return actualiteRepository.save(actualite1);
    }

    public List<Actualite> getAll(){
        List<Actualite> actualites = actualiteRepository.findAll();
        if(actualites.isEmpty())
            throw  new EntityNotFoundException("Aucun actualité trouvé");
        return actualites;
    }

    public List<Actualite> getActualiteByIdAdmin(long idAdmin){
        List<Actualite> actualites = actualiteRepository.getAllActualiteByAdmin_IdAdmin(idAdmin);
        if(actualites == null)
            throw  new EntityNotFoundException("Aucun actualité trouvé");
        return actualites;
    }
    public String delete(long id){
        Actualite actualite = actualiteRepository.findByIdActualite(id);
        if(actualite == null)
            throw new EntityNotFoundException("Aucun actualité trouvé");
        actualiteRepository.delete(actualite);
        return "Supprimé avec succèss";
    }
}
