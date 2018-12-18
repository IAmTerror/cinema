// ========== OLD CONTROLLER 1 ===================================================
//package fr.iat.tpcinema.web;
//
//import fr.iat.tpcinema.dao.FilmDao;
//import fr.iat.tpcinema.model.Film;
//import fr.iat.tpcinema.service.ImageManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@RequestMapping(value = "/film")
//public class FilmController {
//
//    @Autowired
//    FilmDao filmDao;
//
//    @Autowired
//    ImageManager imm;
//
//    @GetMapping("/list")
//    public String filmListe(Model model) {
//        model.addAttribute("films", filmDao.getAll());
//        return "film/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String film(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("film", filmDao.getById(idPersonne));
//        return "film/detail";
//    }
//
//    @GetMapping("/modif/{id}")
//    public String filmModif(Model model, @PathVariable("id") String id) {
//        long idFilm = Long.parseLong(id);
//        model.addAttribute("film", filmDao.getById(idFilm));
//        return "film/modif";
//    }
//
//    @GetMapping("/form")
//    public String filmForm(Model model) {
//        model.addAttribute("film", new Film());
//        return "film/form";
//    }
//
//    @PostMapping("/update")
//    public String filmUpdate(@ModelAttribute Film film){
//        filmDao.save(film);
//        return "redirect:/film/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String filmDelete(@PathVariable("id") Long id){
//        filmDao.delete(id);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String filmAdd(@ModelAttribute Film film){
//        filmDao.save(film);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String submit(@RequestParam("poster") MultipartFile file, @ModelAttribute Film film){
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
//}

package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmDao;
import fr.iat.tpcinema.model.Film;
import fr.iat.tpcinema.service.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmDao filmDao;

    @Autowired
    ImageManager imm;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Film> films = filmDao.findAll();
        model.addAttribute("films", films);
        return "film/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmDao.findById(id).get());
        return "film/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmDao.findById(id).get());
        return "film/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("film", new Film());
        return "film/form";
    }

    @GetMapping("/delete/{id}")
    public String filmDelete(@PathVariable("id") Long id){
        filmDao.deleteById(id);
        return "redirect:/film/list";
    }

    @PostMapping("/add")
    public String submit(@RequestParam("poster")MultipartFile file, @ModelAttribute Film film){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePoster(film, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        filmDao.save(film);

        return "redirect:/film/list";
    }
}
