package com.projet_demarche.demarche_facile.service;

import com.projet_demarche.demarche_facile.model.Admin;
import com.projet_demarche.demarche_facile.model.Bureau;
import com.projet_demarche.demarche_facile.model.Document;
import com.projet_demarche.demarche_facile.model.Guide;
import com.projet_demarche.demarche_facile.repository.AdminRepository;
import com.projet_demarche.demarche_facile.repository.BureauRepository;
import com.projet_demarche.demarche_facile.repository.DocumentRepository;
import com.projet_demarche.demarche_facile.repository.GuideRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    public DocumentRepository documentRepository;
    @Autowired
    public AdminRepository adminRepository;
    @Autowired
    public BureauRepository bureauRepository;
    @Autowired
    public GuideRepository guideRepository;

    public Document createDocument(Document document, MultipartFile multipartFileImage, MultipartFile multipartFileAudio,MultipartFile multipartFileFichier) throws Exception {
        Admin admin = adminRepository.findByIdAdmin(document.getAdmin().getIdAdmin());
        Guide guide = guideRepository.findByIdGuide(document.getGuide().getIdGuide());
        if(guide == null)
            throw  new NotFoundException("Selectionner un guide");
        Bureau bureau = bureauRepository.findByIdBureau(document.getBureau().getIdBureau());
         if (bureau == null) {
            throw new Exception("Bureau non trouvé avec l'ID spécifié");
        }

       // document.getBureaux().add(bureau);
        if(admin == null){
            throw new EntityExistsException("Admin non trouvé");
        }else{
            if (documentRepository.findByNom(document.getNom()) == null) {
                //ajout de l'image
                if (multipartFileImage != null) {
                    String location = "C:\\xampp\\htdocs\\demarche";
                    try {
                        Path rootlocation = Paths.get(location);
                        if (!Files.exists(rootlocation)) {
                            Files.createDirectories(rootlocation);
                            Files.copy(multipartFileImage.getInputStream(),
                                    rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            document.setImage("http://10.175.48.39/demarche/"
                                    + multipartFileImage.getOriginalFilename());
                        } else {
                            try {
                                String nom = location + "\\" + multipartFileImage.getOriginalFilename();
                                Path name = Paths.get(nom);
                                if (!Files.exists(name)) {
                                    Files.copy(multipartFileImage.getInputStream(),
                                            rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                                    document.setImage("http://10.175.48.39/demarche/"
                                            + multipartFileImage.getOriginalFilename());
                                } else {
                                    Files.delete(name);
                                    Files.copy(multipartFileImage.getInputStream(), rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                                    document.setImage("http://10.175.48.39/demarche/"
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
                            document.setAudio("http://10.175.48.39/audio_demarche/"
                                    + multipartFileAudio.getOriginalFilename());
                        } else {
                            try {
                                String nom = location + "\\" + multipartFileAudio.getOriginalFilename();
                                Path name = Paths.get(nom);
                                if (!Files.exists(name)) {
                                    Files.copy(multipartFileAudio.getInputStream(),
                                            rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                                    document.setAudio("http://10.175.48.39/audio_demarche/"
                                            + multipartFileAudio.getOriginalFilename());
                                } else {
                                    Files.delete(name);
                                    Files.copy(multipartFileAudio.getInputStream(), rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                                    document.setAudio("http://10.175.48.39/audio_demarche/"
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
                //ajout fichier
                if (multipartFileFichier != null) {
                    String location = "C:\\xampp\\htdocs\\demerche_fichier";
                    try {
                        Path rootlocation = Paths.get(location);
                        if (!Files.exists(rootlocation)) {
                            Files.createDirectories(rootlocation);
                            Files.copy(multipartFileFichier.getInputStream(),
                                    rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                            document.setFichier("http://10.175.48.39/demerche_fichier/"
                                    + multipartFileFichier.getOriginalFilename());
                        } else {
                            try {
                                String nom = location + "\\" + multipartFileFichier.getOriginalFilename();
                                Path name = Paths.get(nom);
                                if (!Files.exists(name)) {
                                    Files.copy(multipartFileFichier.getInputStream(),
                                            rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                                    document.setFichier("http://10.175.48.39/demerche_fichier/"
                                            + multipartFileFichier.getOriginalFilename());
                                } else {
                                    Files.delete(name);
                                    Files.copy(multipartFileFichier.getInputStream(), rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                                    document.setFichier("http://10.175.48.39/demerche_fichier/"
                                            + multipartFileFichier.getOriginalFilename());
                                }
                            } catch (Exception e) {
                                throw new Exception("Impossible de télécharger le fichier");
                            }
                        }
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
            }
        }
        return documentRepository.save(document);
    }


    public Document updateDocument(Document document, long id,MultipartFile multipartFileImage, MultipartFile multipartFileAudio,MultipartFile multipartFileFichier) throws Exception {
        Document document1 = documentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Document non trouvé"));
        document1.setNom(document.getNom());
        document1.setDescription(document.getDescription());
        //image
        if (multipartFileImage != null) {
            String location = "C:\\xampp\\htdocs\\demarche";
            try {
                Path rootlocation = Paths.get(location);
                if (!Files.exists(rootlocation)) {
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFileImage.getInputStream(),
                            rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                    document1.setImage("http://10.175.48.39/demarche/"
                            + multipartFileImage.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFileImage.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFileImage.getInputStream(),
                                    rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            document1.setImage("http://10.175.48.39/demarche/"
                                    + multipartFileImage.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFileImage.getInputStream(), rootlocation.resolve(multipartFileImage.getOriginalFilename()));
                            document1.setImage("http://10.175.48.39/demarche/"
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
        //audio
        if (multipartFileAudio != null) {
            String location = "C:\\xampp\\htdocs\\audio_demarche";
            try {
                Path rootlocation = Paths.get(location);
                if (!Files.exists(rootlocation)) {
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFileAudio.getInputStream(),
                            rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                    document1.setAudio("http://10.175.48.39/audio_demarche/"
                            + multipartFileAudio.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFileAudio.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFileAudio.getInputStream(),
                                    rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                            document1.setAudio("http://10.175.48.39/audio_demarche/"
                                    + multipartFileAudio.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFileAudio.getInputStream(), rootlocation.resolve(multipartFileAudio.getOriginalFilename()));
                            document1.setAudio("http://10.175.48.39/audio_demarche/"
                                    + multipartFileAudio.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception("Impossible de télécharger l\'audio");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }}
            //ajout fichier
            if (multipartFileFichier != null) {
                String location = "C:\\xampp\\htdocs\\demerche_fichier";
                try {
                    Path rootlocation = Paths.get(location);
                    if (!Files.exists(rootlocation)) {
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFileFichier.getInputStream(),
                                rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                        document1.setFichier("http://10.175.48.39/demerche_fichier/"
                                + multipartFileFichier.getOriginalFilename());
                    } else {
                        try {
                            String nom = location + "\\" + multipartFileFichier.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if (!Files.exists(name)) {
                                Files.copy(multipartFileFichier.getInputStream(),
                                        rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                                document1.setFichier("http://10.175.48.39/demerche_fichier/"
                                        + multipartFileFichier.getOriginalFilename());
                            } else {
                                Files.delete(name);
                                Files.copy(multipartFileFichier.getInputStream(), rootlocation.resolve(multipartFileFichier.getOriginalFilename()));
                                document1.setFichier("http://10.175.48.39/demerche_fichier/"
                                        + multipartFileFichier.getOriginalFilename());
                            }
                        } catch (Exception e) {
                            throw new Exception("Impossible de télécharger le fichier");
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
            return documentRepository.save(document1);
        }

        public List<Document> getAllDocument(){
        List<Document> documents = documentRepository.findAll();
        if(documents.isEmpty())
            throw  new EntityNotFoundException("Aucun document trouvé");
        return documents;
        }
        public Document getDocumentById(long id){
        Document document = documentRepository.findByIdDocument(id);
        if(document == null)
            throw new EntityNotFoundException("Aucun document trouvé");
        return document;
        }

    public List<Bureau> findBureauxByNameDocument(String nomDocument) {
        return documentRepository.findBureauxByDocument(nomDocument);
    }
    ////les documents en fonction du guide
    public List<Guide> findGuidesByDocument(String nomDocument) {
        return documentRepository.findGuidesByNomDocument(nomDocument);
    }

    public List<Document> findDocumentByIdGuide(long idGuide){
        List<Document> documents = documentRepository.getAllDocumentByGuide_IdGuide(idGuide);
        if(documents == null )
            throw new EntityNotFoundException("Aucun document trouvé");
        return documents;
    }
  /*  public List<Document> getAllDocumentByGuide(Document document){
        List<Document> documents = documentRepository.findGuideByDocumentName(document.getNom()).getDocuments();
        if(documents.isEmpty())
            throw new EntityNotFoundException("Aucun document trouvé");
        return documents;
    }*/

    public String delete (long id){
    Document document = documentRepository.findByIdDocument(id);
    if(document == null)
        throw new EntityNotFoundException("Document non trouvé");
    documentRepository.deleteById(id);
    return "Supprimé avec succèss";
    }

}
