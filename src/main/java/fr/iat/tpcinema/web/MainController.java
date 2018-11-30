package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    FilmsDao filmsDao = new FilmsDao();

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("nom", "Karl");
        model.addAttribute("films", filmsDao.films());
        return "index";
    }

    // Version avec @RequestParam
//    @GetMapping("/film")
//    public String detail(Model model, @RequestParam("id") String id) {
//        Integer idFilm = Integer.parseInt(id);
//        model.addAttribute("film", filmsDao.getById(idFilm));
//        return "detail";
//    }

    // Version avec @PathVariable
    @GetMapping("/film/{id}")
    public String detail(Model model, @PathVariable("id") String id) {
        Integer idFilm = Integer.parseInt(id);
        model.addAttribute("film", filmsDao.getById(idFilm));
        return "detail";
    }
}
