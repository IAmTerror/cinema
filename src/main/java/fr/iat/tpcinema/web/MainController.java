package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
