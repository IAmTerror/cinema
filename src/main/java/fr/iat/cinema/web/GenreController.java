//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.GenreDao;
//import fr.iat.cinema.model.Genre;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping(value = "/genre")
//public class GenreController {
//
//    @Autowired
//    GenreDao genreDao;
//
//
//    @GetMapping("/list")
//    public String list(Model model){
//        model.addAttribute("genres", genreDao.findAllByOrderByIdAsc());
//        return "genre/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable("id") long id, Model model){
//        model.addAttribute("genre", genreDao.findById(id).get());
//        return "genre/detail";
//    }
//
//    @GetMapping("/mod/{id}")
//    public String mod(@PathVariable("id")long id, Model model){
//        model.addAttribute("genre", genreDao.findById(id).get());
//        return "genre/form";
//    }
//
//    @GetMapping("/add")
//    public String add(Model model){
//        model.addAttribute("genre", new Genre());
//        return "genre/form";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        genreDao.deleteById(id);
//        return "redirect:/genre/list";
//    }
//
//    @PostMapping("/add")
//    public String submit(@ModelAttribute Genre genre){
//        genreDao.save(genre);
//        return "redirect:/genre/list";
//    }
//}

package fr.iat.cinema.web;

import fr.iat.cinema.model.Genre;
import fr.iat.cinema.service.GenreManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/genre")
public class GenreController {

    /**
     *
     */
    GenreManager genreManager;

    /**
     *
     * @param genreManager
     */
    public GenreController(GenreManager genreManager){
        this.genreManager = genreManager;
        assert(genreManager != null);
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("")
    public String main(Model model){
        model.addAttribute("genres", genreManager.getAll());
        model.addAttribute("newgenre", new Genre());
        return "genre/form";
    }

}

