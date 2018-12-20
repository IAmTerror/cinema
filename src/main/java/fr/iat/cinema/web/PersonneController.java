// ========== OLD CONTROLLER 1 ===================================================
//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.PersonneDao;
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.model.Personne;
//import fr.iat.cinema.service.ImageManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//
//@Controller
//@RequestMapping(value = "/person")
//public class PersonneController {
//
//    @Autowired
//    PersonneDao personneDao;
//
//    @Autowired
//    ImageManager imm;
//
////    @Autowired
////    RoleDao roleDao;
//
////    @Autowired
////    private Path path;
//
//
//    @GetMapping("/list")
//    public String personnesListe(Model model) {
//        model.addAttribute("personnes", personneDao.getAll());
//        return "person/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String personne(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("personne", personneDao.getById(idPersonne));
//        return "person/detail";
//    }
//
//    @GetMapping("/form")
//    public String personneForm(Model model) {
//        model.addAttribute("personne", new Personne());
//        return "person/form";
//    }
//
//    @GetMapping("/modif/{id}")
//    public String personneModif(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("personne", personneDao.getById(idPersonne));
//        return "person/modif";
//    }
//
//    @PostMapping("/update")
//    public String personneUpdate(@ModelAttribute Personne personne){
//        personneDao.save(personne);
//        return "redirect:/person/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String personneDelete(@PathVariable("id") Long id){
//        personneDao.delete(id);
//        return "redirect:/person/list";
//    }
//
////    @PostMapping("/add")
////    public String personneAdd(@ModelAttribute Personne personne){
////        personneDao.save(personne);
////        return "redirect:/person/list";
////    }
//
//    @PostMapping("/add")
//    public String submit(@RequestParam("photo") MultipartFile file, @ModelAttribute Personne personne){
//        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try {
//                imm.savePhoto(personne, file.getInputStream());
//            } catch (IOException ioe){
//                System.out.println("Erreur lecture : "+ioe.getMessage());
//            }
//        }
//        personneDao.save(personne);
//        return "redirect:/person/list";
//    }
//
////    // ========== AFFICHES =======================================================
////    @GetMapping("/affiches/{id}")
////    public void affiche(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {
////
//////        String affichesPath="C:\\Users\\cyril\\OUTER HEAVEN\\CDA\\varni\\tp-springboot\\sources\\affiches\\";
////        String filename = path.getAffiche() + id;
////
////        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
////        String mime = request.getServletContext().getMimeType(filename);
////        if (mime == null) {
////            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
////            return;
////        }
////        response.setContentType(mime);
////        File file = new File(filename);
////        response.setContentLength((int) file.length());
////        FileInputStream in = new FileInputStream(file);
////        OutputStream out = response.getOutputStream();
////        byte[] buf = new byte[1024];
////        int count = 0;
////        while ((count = in.read(buf)) >= 0) {
////            out.write(buf, 0, count);
////        }
////        out.close();
////        in.close();
////    }
////
////    @GetMapping("/personnes/{id}")
////    public void personne(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {
////
////        String filename = path.getPersonne() + id;
////
////        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
////        String mime = request.getServletContext().getMimeType(filename);
////        if (mime == null) {
////            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
////            return;
////        }
////        response.setContentType(mime);
////        File file = new File(filename);
////        response.setContentLength((int) file.length());
////        FileInputStream in = new FileInputStream(file);
////        OutputStream out = response.getOutputStream();
////        byte[] buf = new byte[1024];
////        int count = 0;
////        while ((count = in.read(buf)) >= 0) {
////            out.write(buf, 0, count);
////        }
////        out.close();
////        in.close();
////    }
//}

package fr.iat.cinema.web;

import fr.iat.cinema.dao.PersonneDao;
import fr.iat.cinema.model.Personne;
import fr.iat.cinema.service.ImageManager;
import fr.iat.cinema.service.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "/person")
public class PersonneController {

    @Autowired
    PersonneDao personneDao;

    @Autowired
    ImageManager imm;

    @Autowired
    Path path;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("personnes", personneDao.findAllByOrderByIdAsc());
        return "person/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model){
        model.addAttribute("personne", personneDao.findById(id).get());
        return "person/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("personne", personneDao.findById(id).get());
        return "person/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("personne", new Personne());
        return "person/form";
    }

    @GetMapping("/delete/{id}")
    public String personneDelete(@PathVariable("id") Long id){
        personneDao.deleteById(id);
        return "redirect:/person/list";
    }

    @PostMapping("/add")
    public String submit(@RequestParam("photo") MultipartFile file, @ModelAttribute Personne personne){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePhoto(personne, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        personneDao.save(personne);
        return "redirect:/person/list";
    }


    // ============ POSTERS PERSONNES ============ //
    @GetMapping("/personnes/{id}")
    public void personne(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {

        String filename = path.getPersonne() + id;

        String mime = request.getServletContext().getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int) file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }
}