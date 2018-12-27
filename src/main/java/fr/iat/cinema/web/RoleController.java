// ========== OLD CONTROLLER 1 ===================================================
//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.PersonDao;
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.model.Person;
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
//    PersonDao roleDao;
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
//        model.addAttribute("persons", roleDao.getAll());
//        return "person/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String person(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("person", roleDao.getById(idPersonne));
//        return "person/detail";
//    }
//
//    @GetMapping("/form")
//    public String personneForm(Model model) {
//        model.addAttribute("person", new Person());
//        return "person/form";
//    }
//
//    @GetMapping("/modif/{id}")
//    public String personneModif(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("person", roleDao.getById(idPersonne));
//        return "person/modif";
//    }
//
//    @PostMapping("/update")
//    public String personneUpdate(@ModelAttribute Person person){
//        roleDao.save(person);
//        return "redirect:/person/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String personneDelete(@PathVariable("id") Long id){
//        roleDao.delete(id);
//        return "redirect:/person/list";
//    }
//
////    @PostMapping("/add")
////    public String personneAdd(@ModelAttribute Person person){
////        roleDao.save(person);
////        return "redirect:/person/list";
////    }
//
//    @PostMapping("/add")
//    public String submit(@RequestParam("photo") MultipartFile file, @ModelAttribute Person person){
//        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try {
//                imm.savePhoto(person, file.getInputStream());
//            } catch (IOException ioe){
//                System.out.println("Erreur lecture : "+ioe.getMessage());
//            }
//        }
//        roleDao.save(person);
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
////    @GetMapping("/persons/{id}")
////    public void person(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {
////
////        String filename = path.getPerson() + id;
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

import fr.iat.cinema.dao.FilmDao;
import fr.iat.cinema.dao.PersonDao;
import fr.iat.cinema.dao.RoleDao;
import fr.iat.cinema.model.Person;
import fr.iat.cinema.model.Role;
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

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    RoleDao roleDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    FilmDao filmDao;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("roles", roleDao.findAllByOrderByIdAsc());
        return "role/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model){
        model.addAttribute("role", roleDao.findById(id).get());
//        model.addAttribute("person", personDao.findById(id).get());
//        model.addAttribute("film", filmDao.findById(id).get());
        return "role/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("role", roleDao.findById(id).get());
        return "role/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("role", new Role());
        return "role/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        roleDao.deleteById(id);
        return "redirect:/role/list";
    }
}