package fr.iat.cinema.web;

import fr.iat.cinema.dao.TmdbFilmDao;
import fr.iat.cinema.service.ImportFilmsFromTMDB;
import fr.iat.cinema.service.ImportPersonsFromTMDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private ImportFilmsFromTMDB importFilmsFromTMDB;

    @Autowired
    private ImportPersonsFromTMDB importPersonsFromTMDB;

    @GetMapping("/populate/films")
    public String populateFilms(Model model) {

        model.addAttribute("nom", "Cyril");

//        importFilmsFromTMDB.ImportFilmsViaOnlineTmdbFile();
        importFilmsFromTMDB.ImportFilmsViaLocalTempTmdbFile();

        //TODO: devra plus tard retourner une interface d'administration plutôt que l'index
        return "index";
    }

    @GetMapping("/populate/persons")
    public String populatePersons(Model model) {

        model.addAttribute("nom", "Cyril");

//        importPersonsFromTMDB.ImportPersonsViaOnlineTmdbFile();
        importPersonsFromTMDB.ImportPersonsViaLocalTempTmdbFile();

        //TODO: devra plus tard retourner une interface d'administration plutôt que l'index
        return "index";
    }
}
