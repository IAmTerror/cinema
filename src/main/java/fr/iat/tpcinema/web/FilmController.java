package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmDao;
import fr.iat.tpcinema.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/film")
public class FilmController {

    @Autowired
    FilmDao filmDao;

    @GetMapping("/list")
    public String filmListe(Model model) {
        model.addAttribute("films", filmDao.getAll());
        return "film/list";
    }

    @GetMapping("/detail/{id}")
    public String film(Model model, @PathVariable("id") String id) {
        long idPersonne = Long.parseLong(id);
        model.addAttribute("film", filmDao.getById(idPersonne));
        return "film/detail";
    }

    @GetMapping("/form")
    public String filmForm(Model model) {
        model.addAttribute("film", new Film());
        return "film/form";
    }

    @PostMapping("/add")
    public String filmAdd(@ModelAttribute Film film){
        filmDao.save(film);
        return "redirect:/film/list";
    }

    @GetMapping("/modif/{id}")
    public String filmModif(Model model, @PathVariable("id") String id) {
        long idFilm = Long.parseLong(id);
        model.addAttribute("film", filmDao.getById(idFilm));
        return "film/modif";
    }

    @PostMapping("/update")
    public String filmUpdate(@ModelAttribute Film film){
        filmDao.save(film);
        return "redirect:/film/list";
    }

    @GetMapping("/delete/{id}")
    public String filmDelete(@PathVariable("id") Long id){
        filmDao.delete(id);
        return "redirect:/film/list";
    }
}
