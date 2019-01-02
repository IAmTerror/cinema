// ========== OLD CONTROLLER 1 ===================================================
//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.FilmDao;
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.service.ImageManager;
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
//    FilmDao reviewDao;
//
//    @Autowired
//    ImageManager imm;
//
//    @GetMapping("/list")
//    public String filmListe(Model model) {
//        model.addAttribute("films", reviewDao.getAll());
//        return "film/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String film(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("film", reviewDao.getById(idPersonne));
//        return "film/detail";
//    }
//
//    @GetMapping("/modif/{id}")
//    public String filmModif(Model model, @PathVariable("id") String id) {
//        long idFilm = Long.parseLong(id);
//        model.addAttribute("film", reviewDao.getById(idFilm));
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
//        reviewDao.save(film);
//        return "redirect:/film/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String filmDelete(@PathVariable("id") Long id){
//        reviewDao.delete(id);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String filmAdd(@ModelAttribute Film film){
//        reviewDao.save(film);
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
//        reviewDao.save(film);
//
//        return "redirect:/film/list";
//    }
//}

package fr.iat.cinema.web;

import fr.iat.cinema.dao.ReviewDao;
import fr.iat.cinema.model.Review;
import fr.iat.cinema.service.ImageManager;
import fr.iat.cinema.service.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    ImageManager imm;

    @Autowired
    Path path;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Review> reviews = reviewDao.findAllByOrderByIdAsc();
        model.addAttribute("reviews", reviews);
        return "reviewDao/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){
        model.addAttribute("review", reviewDao.findById(id).get());
        return "reviewDao/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("review", reviewDao.findById(id).get());
        return "reviewDao/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("review", new Review());
        return "reviewDao/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        reviewDao.deleteById(id);
        return "redirect:/review/list";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute Review review){
        reviewDao.save(review);
        return "redirect:/review/list";
    }
}
