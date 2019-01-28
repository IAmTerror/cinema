package fr.iat.cinema.web;

import fr.iat.cinema.service.ImportFilmsFromTMDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/populate")
    public String populate(Model model) {

        model.addAttribute("nom", "Cyril");

        ImportFilmsFromTMDB importFilmsFromTMDB = new ImportFilmsFromTMDB();

        //TODO: devra plus tard retourner une interface d'administration plutôt que l'index
        return "index";
    }
}
