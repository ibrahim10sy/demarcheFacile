package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.Exception.NoContentException;
import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    //création de admin

    public Admin createAdmin(Admin admin, MultipartFile multipartFile) throws Exception {
        if(adminRepository.findByEmail(admin.getEmail()) == null){
            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\demarche";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),
                                rootlocation.resolve(multipartFile.getOriginalFilename()));
                        admin.setImage("http://localhost:8080/demarche"
                                +multipartFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),
                                        rootlocation.resolve(multipartFile.getOriginalFilename()));
                                admin.setImage("http://localhost:8080/demarche"
                                        +multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                admin.setImage("http://localhost:8080/demarche"
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
            return  adminRepository.save(admin);
        }else{
            throw new EntityExistsException("Cet compte existe déjà");
        }
    }

    public List<Admin> getAllAdmin(){
        List<Admin> admins = adminRepository.findAll();
        if(admins.isEmpty()){
            throw new NoContentException("Aucun donnée trouvé");
        }
        return admins;
    }

    public Admin getAdminById(long id){
        Admin admin = adminRepository.findByIdAdmin(id);
        if(admin == null){
            throw new EntityNotFoundException("Aucun donné trouvé avec l'ID :"+id);
        }
        return admin;
    }

    public Admin updateAdmin(Admin admin, long id, MultipartFile multipartFile) throws Exception {
        Admin admin1 = adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("utilisateur non trouvé"));

        admin1.setNom(admin.getNom());
        admin1.setPrenom(admin.getPrenom());
        admin1.setEmail(admin.getEmail());
        admin1.setMotDePasse(admin.getMotDePasse());

        if(multipartFile != null){
            String location = "C:\\xampp\\htdocs\\demarche";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(),
                            rootlocation.resolve(multipartFile.getOriginalFilename()));
                    admin1.setImage("http://localhost/demarche/"
                            +multipartFile.getOriginalFilename());
                }else{
                    try {
                        String nom = location+"\\"+multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFile.getInputStream(),
                                    rootlocation.resolve(multipartFile.getOriginalFilename()));
                            admin1.setImage("http://localhost/demarche/"
                                    +multipartFile.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                            admin1.setImage("http://localhost/demarche/"
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
           return adminRepository.save(admin1);
        }

        public  String deleteAdmin(Admin admin){
        Admin admin1 = adminRepository.findByIdAdmin(admin.getIdAdmin());
        if(admin1 == null)
            throw new EntityNotFoundException("Ce compte n'existe pas");
        adminRepository.delete(admin1);
        return "Compte supprimé avec succèss";
        }

       /* public Admin connexion(String email, String motDePasse) throws AuthenticationException {
        Admin admin = adminRepository.findByEmailAndMotDePasse(email,motDePasse);
        if(admin == null)
            throw new AuthenticationException("Invalid email or password");
        return admin;
        }*/
    public Admin connexion(String email, String motDePasse){
        if(adminRepository.findByEmailAndMotDePasse(email,motDePasse) != null){
            return adminRepository.findByEmailAndMotDePasse(email, motDePasse);
        }else{
            throw  new NotFoundException("Ce compte n'existe pas");
        }
    }

}

