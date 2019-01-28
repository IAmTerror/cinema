//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.FilmDao;
//import fr.iat.cinema.dao.GenreDao;
//import fr.iat.cinema.dao.PersonDao;
//import fr.iat.cinema.dao.RoleDao;
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.service.ImageManager;
//import fr.iat.cinema.service.Path;
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
//@Controller
//@RequestMapping("/film")
//public class FilmController {
//
//    @Autowired
//    FilmDao filmDao;
//
//    @Autowired
//    PersonDao personDao;
//
//    @Autowired
//    GenreDao genreDao;
//
//    @Autowired
//    ImageManager imm;
//
//    @Autowired
//    Path path;
//
//    @GetMapping("/list")
//    public String list(Model model){
//        Iterable<Film> films = filmDao.findAllByOrderByIdAsc();
//        model.addAttribute("films", films);
//        return "film/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable("id")long id, Model model){
//        model.addAttribute("film", filmDao.findById(id).get());
//        return "film/detail";
//    }
//
//    @GetMapping("/mod/{id}")
//    public String mod(@PathVariable("id")long id, Model model){
//        model.addAttribute("film", filmDao.findById(id).get());
//        model.addAttribute("persons", personDao.findAll());
//        model.addAttribute("genres", genreDao.findAll());
//        return "film/form";
//    }
//
//    @GetMapping("/add")
//    public String add(Model model){
//        model.addAttribute("film", new Film());
//        model.addAttribute("persons", personDao.findAll());
//        model.addAttribute("genres", genreDao.findAll());
//        return "film/form";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        filmDao.deleteById(id);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String submit(@RequestParam("poster")MultipartFile file, @ModelAttribute Film film){
//        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try {
//                imm.savePoster(film, file.getInputStream());
//            } catch (IOException ioe){
//                System.out.println("Erreur lecture : "+ioe.getMessage());
//            }
//        }
//        filmDao.save(film);
//
//        return "redirect:/film/list";
//    }
//
//    // ========== AFFICHES =======================================================
//    @GetMapping("/affiches/{id}")
//    public void affiche(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {
//
////        String affichesPath="C:\\Users\\cyril\\OUTER HEAVEN\\CDA\\varni\\tp-springboot\\sources\\affiches\\";
//        String filename = path.getAffiche() + id;
//
//        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
//        String mime = request.getServletContext().getMimeType(filename);
//        if (mime == null) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return;
//        }
//        response.setContentType(mime);
//        File file = new File(filename);
//        response.setContentLength((int) file.length());
//        FileInputStream in = new FileInputStream(file);
//        OutputStream out = response.getOutputStream();
//        byte[] buf = new byte[1024];
//        int count = 0;
//        while ((count = in.read(buf)) >= 0) {
//            out.write(buf, 0, count);
//        }
//        out.close();
//        in.close();
//    }
//}

package fr.iat.cinema.web;


import fr.iat.cinema.model.Film;
import fr.iat.cinema.model.Role;
import fr.iat.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/film")
public class FilmController {


    @Autowired
    FilmManager filmManager;

    @Autowired
    PersonManager personManager;

    @Autowired
    GenreManager genreManager;

    @Autowired
    Path path;

    @Autowired
    ImageManager imm;

    @GetMapping("/list")
    public String list(Model model) {
        Iterable<Film> films = filmManager.getAll();
        model.addAttribute("films", films);
        return "film/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", filmManager.getById(id));
        return "film/detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("title", "Ajout d'un film");
        model.addAttribute("film", new Film());
        return "film/form";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id") long id, Model model) {
        Film film = filmManager.getById(id);
        model.addAttribute("title", film.getTitle() + " : modification");
        model.addAttribute("persons", personManager.getAll());
        model.addAttribute("genresFilm", genreManager.getAll());
        model.addAttribute("film", film);
        model.addAttribute("plays", film.getRoles());
        model.addAttribute("newrole", new Role());
        return "film/form";
    }



    @PostMapping("/add")
    public String submit(@ModelAttribute Film film) {
        filmManager.save(film);
        return "redirect:list";
    }

    @GetMapping("/rmrole/{role_id}")
    public String rmRole(@PathVariable("role_id") Long roleId) {
        long filmId = filmManager.removeRole(roleId);

        return "redirect:/film/mod/" + filmId;
    }

    @PostMapping("/addrole")
    public String addRole(@ModelAttribute Role role) {
        long filmId = role.getFilm().getId();
        filmManager.addRole(filmId, role);
        return "redirect:/film/mod/" + filmId;
    }

    @PostMapping("/modrole/{id}")
    public String modRole(@PathVariable("id") long roleId, @ModelAttribute Role role) {
        filmManager.saveRole(role);
        return "redirect:/film/mod/" + role.getFilm().getId();
    }

        // ========== AFFICHES =======================================================
    @GetMapping("/affiches/{id}")
    public void affiche(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {

//        String affichesPath="C:\\Users\\cyril\\OUTER HEAVEN\\CDA\\varni\\tp-springboot\\sources\\affiches\\";
        String filename = path.getAffiche() + id;

        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
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

