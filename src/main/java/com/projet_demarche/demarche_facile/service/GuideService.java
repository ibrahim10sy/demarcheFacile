package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Guide;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import com.projet_demarche.demarche_facile.repository.GuideRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class GuideService {

    @Autowired
    public GuideRepository guideRepository;
    @Autowired
    public AdminRepository adminRepository;

    //methode ajout
    public Guide createGuide(Guide guide, MultipartFile multipartFileImage, MultipartFile multipartFileAudio) throws Exception {
        Admin admin = adminRepository.findByIdAdmin(guide.getAdmin().getIdAdmin());
        if(admin == null) {
            throw new EntityExistsException("Compte non trouvé");
        }else {
            if (guideRepository.findByLibelle(guide.getLibelle()) == null) {
                //ajout de l'image
                if (multipartFileImage != null) {
                    String location = "C:\\xampp\\htdocs\\demarche";
                    try {
                        Path rootlocation = Paths.get(location);
                        if (!Files.exists(rootlocation)) {
                            Files.createDirectories(rootlocation);
                            Files.copy(multipartFileImage.getInputStream(),
                                    rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            guide.setImage("http://10.175.48.71/demarche/"
                                    + multipartFileImage.getOriginalFilename());
                        } else {
                            try {
                                String nom = location + "\\" + multipartFileImage.getOriginalFilename();
                                Path name = Paths.get(nom);
                                if (!Files.exists(name)) {
                                    Files.copy(multipartFileImage.getInputStream(),
                                            rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                                    guide.setImage("http://10.175.48.71/demarche/"
                                            + multipartFileImage.getOriginalFilename());
                                } else {
                                    Files.delete(name);
                                    Files.copy(multipartFileImage.getInputStream(), rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                                    guide.setImage("http://10.175.48.71/demarche/"
                                            + multipartFileImage.getOriginalFilename());
                                }
                            } catch (Exception e) {
                                throw new Exception("Impossible de télécharger l\'image");
                            }
                        }
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
                //ajout de l'audio
                if (multipartFileAudio != null) {
                    String location = "C:\\xampp\\htdocs\\audio_demarche";
                    try {
                        Path rootlocation = Paths.get(location);
                        if (!Files.exists(rootlocation)) {
                            Files.createDirectories(rootlocation);
                            Files.copy(multipartFileAudio.getInputStream(),
                                    rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                            guide.setAudio("http://10.175.48.71/audio_demarche/"
                                    + multipartFileAudio.getOriginalFilename());
                        } else {
                            try {
                                String nom = location + "\\" + multipartFileAudio.getOriginalFilename();
                                Path name = Paths.get(nom);
                                if (!Files.exists(name)) {
                                    Files.copy(multipartFileAudio.getInputStream(),
                                            rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                                    guide.setAudio("http://10.175.48.71/audio_demarche/"
                                            + multipartFileAudio.getOriginalFilename());
                                } else {
                                    Files.delete(name);
                                    Files.copy(multipartFileAudio.getInputStream(), rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                                    guide.setAudio("http://10.175.48.71/audio_demarche/"
                                            + multipartFileAudio.getOriginalFilename());
                                }
                            } catch (Exception e) {
                                throw new Exception("Impossible de télécharger l\'audio");
                            }
                        }
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
                return guideRepository.save(guide);
            } else {
                throw new EntityExistsException("Ce guide existe déjà");
            }
        }
    }
  /*  public Guide createGuide(Guide guide, MultipartFile multipartFileImage, MultipartFile multipartFileAudio) throws Exception {
        Admin admin = adminRepository.findByIdAdmin(guide.getAdmin().getIdAdmin());
        if(admin == null) {
            throw new EntityNotFoundException("Compte non trouvé");
        } else {
            if (guideRepository.findByLibelle(guide.getLibelle()) != null) {
                throw new EntityExistsException("Ce guide existe déjà");
            } else {
                try {
                    if (multipartFileImage != null) {
                        // Logique de gestion de l'image
                        String imageUrl = saveFile(multipartFileImage, "C:\\xampp\\htdocs\\demarche");
                        guide.setImage(imageUrl);
                    }
                    if (multipartFileAudio != null) {
                        // Logique de gestion de l'audio
                        String audioUrl = saveFile(multipartFileAudio, "C:\\xampp\\htdocs\\audio_demarche");
                        guide.setAudio(audioUrl);
                    }
                    return guideRepository.save(guide);
                } catch (Exception e) {
                    throw new Exception("Erreur lors de la création du guide : " + e.getMessage());
                }
            }
        }
    }

    private String saveFile(MultipartFile file, String location) throws Exception {
        try {
            Path rootLocation = Paths.get(location);
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
            String fileName = file.getOriginalFilename();
            Path filePath = rootLocation.resolve(fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.copy(file.getInputStream(), filePath);
            return "http://10.175.48.71/" + location + "/" + fileName;
        } catch (Exception e) {
            throw new Exception("Impossible de télécharger le fichier : " + e.getMessage());
        }
    }
*/
    //methode update
    public Guide updateGuide(Guide guide,long id, MultipartFile multipartFileImage, MultipartFile multipartFileAudio) throws Exception {
        Guide guide1 = guideRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Guide non trouvé"));
        guide1.setLibelle(guide.getLibelle());
        guide1.setDescription(guide1.getDescription());

        //modification de l'image
        if(multipartFileImage != null){
            String location = "C:\\xampp\\htdocs\\demarche";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFileImage.getInputStream(),
                            rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                    guide1.setImage("http://10.175.48.71/demarche/"
                            +multipartFileImage.getOriginalFilename());
                }else{
                    try {
                        String nom = location+"\\"+multipartFileImage.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFileImage.getInputStream(),
                                    rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            guide1.setImage("http://10.175.48.71/demarche/"
                                    +multipartFileImage.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFileImage.getInputStream(),rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            guide1.setImage("http://10.175.48.71/demarche/"
                                    +multipartFileImage.getOriginalFilename());
                        }
                    }catch (Exception e){
                        throw new Exception("Impossible de télécharger l\'image");
                    }
                }
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
        //modification de l'audio
        if(multipartFileAudio != null){
            String location = "C:\\xampp\\htdocs\\audio_demarche";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFileAudio.getInputStream(),
                            rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                    guide1.setAudio("http://10.175.48.71/audio_demarche/"
                            +multipartFileAudio.getOriginalFilename());
                }else{
                    try {
                        String nom = location+"\\"+multipartFileAudio.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFileAudio.getInputStream(),
                                    rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                            guide1.setAudio("http://10.175.48.71/audio_demarche/"
                                    +multipartFileAudio.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFileAudio.getInputStream(),rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                            guide1.setAudio("http://10.175.48.71/audio_demarche/"
                                    +multipartFileAudio.getOriginalFilename());
                        }
                    }catch (Exception e){
                        throw new Exception("Impossible de télécharger l\'audio");
                    }
                }
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
        return guideRepository.save(guide1);
    }

    public Guide getGuideById(long id){
        Guide guide1 = guideRepository.findByIdGuide(id);
        if(guide1 == null)
            throw new EntityExistsException("Aucun guide trouvé avec ID"+id);
        return guide1;
    }

    public List<Guide> getAllGuide(){
        List<Guide> guide = guideRepository.findAll();
        return  guide;
    }
    public String delete(long id){
        Guide guide1 = guideRepository.findByIdGuide(id);
        if(guide1 == null)
            throw new EntityExistsException("Impossible de supprimé car aucun guide trouvé");
        guideRepository.deleteById(id);
        return "Supprimé avec succèss";
    }
}
