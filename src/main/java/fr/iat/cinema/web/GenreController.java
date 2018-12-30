package fr.iat.cinema.web;

import fr.iat.cinema.dao.GenreDao;
import fr.iat.cinema.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    GenreDao genreDao;


    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("genres", genreDao.findAllByOrderByIdAsc());
        return "genre/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model){
        model.addAttribute("genre", genreDao.findById(id).get());
        return "genre/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("genre", genreDao.findById(id).get());
        return "genre/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("genre", new Genre());
        return "genre/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        genreDao.deleteById(id);
        return "redirect:/genre/list";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute Genre genre){
        genreDao.save(genre);
        return "redirect:/genre/list";
    }
}